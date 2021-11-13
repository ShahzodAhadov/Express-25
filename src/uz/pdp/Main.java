package uz.pdp;

import uz.pdp.model.*;
import uz.pdp.model.base.Response;
import uz.pdp.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        Admin admin = new Admin("admin", "123");
        UsersService usersService = new UsersService();
        CardsService cardsService = new CardsService();
        CategoriesService categoriesService = new CategoriesService();
        SubcategoriesService subcategoriesService = new SubcategoriesService();
        ProductsService productsService = new ProductsService();
        LocationsService locationsService = new LocationsService();
        HistoriesService historiesService = new HistoriesService();
        CafesService cafesService = new CafesService();
        int stepCode = 1;
        while (stepCode != 0) {
            System.out.println("1.SIGN IN ADMIN\t 2.SIGN IN USER\t 3.SIGN UP USER\t 4.SIGN IN CAFE\t " +
                    "5.SIGN UP CAFE\t 0.EXIT");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1:
                    boolean isSuccess = signInAdmin(admin);
                    if (!isSuccess)
                        System.out.println(Response.ADMIN_INCORRECT);
                    else
                        menuAdmin(usersService, cardsService, categoriesService, subcategoriesService, productsService);
                    break;
                case 2:
                    User user = signInUser(usersService);
                    if (user != null) {
                        menuUser(user, cardsService, categoriesService, subcategoriesService, locationsService, historiesService, productsService, cafesService);
                    }
                    break;
                case 3:
                    user = signUpUser(usersService);
                    if (user != null) {
                        menuUser(user, cardsService, categoriesService, subcategoriesService, locationsService, historiesService, productsService, cafesService);
                    } else {
                        System.out.println(Response.PHONE_INVALID);
                        System.out.println(Response.REPEAT);
                    }
                    break;
                case 4:
                    Cafe cafe = signInCafe(cafesService);
                    if (cafe != null) {
                        menuCafe(cafe, categoriesService, subcategoriesService, productsService, historiesService, cardsService);
                    } else {
                        System.out.println(Response.CAFE_INCORRECT);
                    }
                    break;
                case 5:
                    cafe = signUpCafe(cafesService);
                    if (cafe != null) {
                        System.out.println(Response.SUCCESS);
                        menuCafe(cafe, categoriesService, subcategoriesService, productsService, historiesService, cardsService);
                    } else {
                        System.out.println(Response.REPEAT);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println(Response.ENTER_SWITCH_DEFAULT);
            }
        }
    }

    public static boolean signInAdmin(Admin admin) {
        Scanner scannerStr = new Scanner(System.in);
        System.out.println(Response.ENTER_USERNAME);
        String username = scannerStr.next();
        System.out.println(Response.ENTER_PASSWORD);
        String password = scannerStr.next();
        return (admin.getUsername().equals(username) && admin.getPassword().equals(password));
    }

    public static void menuAdmin(UsersService usersService, CardsService cardsService, CategoriesService categoriesService, SubcategoriesService subcategoriesService, ProductsService productsService) {
        Scanner scannerInt = new Scanner(System.in);
        int stepCode = 1;
        while (stepCode != 0) {
            System.out.println("1.SET DISCOUNT PERCENT\t 2.SET PERCENT FOR CAFE\t 3.SEE PERCENTS\t " +
                    "4.SEE BALANCE\t 5.GET INFO ABOUT USER");
            System.out.println("6.ADD CATEGORY\t 7.ADD SUBCATEGORY\t 0.EXIT");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1:
                    System.out.println(Response.ENTER_AMOUNT);
                    Account.setDiscountPercent(scannerInt.nextDouble());
                    break;
                case 2:
                    System.out.println(Response.ENTER_AMOUNT);
                    Account.setCafePercent(scannerInt.nextDouble());
                    break;
                case 3:
                    System.out.println("CASHBACK PERCENT: " + Account.getDiscountPercent());
                    System.out.println("PERCENT FOR CAFE: " + Account.getCafePercent());
                    break;
                case 4:
                    System.out.println("SYSTEM BALANCE: " + Account.getSystemBalance());
                    break;
                case 5:
                    int result = getInfoUser(usersService, cardsService);
                    if (result == -1) {
                        System.out.println(Response.USER_NOT_FOUND);
                        System.out.println(Response.REPEAT);
                    } else if (result == 0)
                        System.out.println("The user do not have cards");
                    break;
                case 6:
                    result = addCategory(categoriesService);
                    if (result == 0) {
                        System.out.println(Response.CATEGORY_INVALID);
                    } else {
                        System.out.println(Response.SUCCESS);
                    }
                    break;
                case 7:
                    result = addSubcategory(categoriesService, subcategoriesService);
                    if (result == -1) {
                        System.out.println(Response.ENTER_SWITCH_DEFAULT);
                    } else if (result == -2) {
                        System.out.println(Response.SUBCATEGORY_INVALID);
                    } else if (result == -3) {
                        System.out.println("There are no categories");
                    } else {
                        System.out.println(Response.SUCCESS);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println(Response.ENTER_SWITCH_DEFAULT);
            }
        }
    }

    public static int getInfoUser(UsersService usersService, CardsService cardsService) {
        Scanner scannerStr = new Scanner(System.in);
        System.out.println(Response.ENTER_PHONE_NUMBER);
        String phoneNumber = scannerStr.next();
        User user = usersService.get(phoneNumber);
        if (user == null)
            return -1;
        List<Card> cards = cardsService.getList(user);
        if (cards.size() == 0)
            return 0;
        int index = 0;
        for (Card card : cards) {
            System.out.println(++index + ". Card name: " + card.getName() + " Cara number: " +
                    card.getCardNumber() + " Balance: " + card.getBalance());
        }
        return 1;
    }

    public static int addCategory(CategoriesService categoriesService) {
        Scanner scannerStr = new Scanner(System.in);
        System.out.println(Response.ENTER_CATEGORY);
        String name = scannerStr.nextLine();
        boolean isValid = categoriesService.check(name);
        if (!isValid) {
            return 0;
        }
        Category category = new Category(name);
        categoriesService.add(category);
        return 1;
    }

    public static int addSubcategory(CategoriesService categoriesService, SubcategoriesService subcategoriesService) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        List<Category> categories = categoriesService.getList();
        int index = 0;
        if (categories.size() == 0) {
            return -3;
        }
        for (Category category : categories) {
            System.out.print(++index + "." + category.getName() + "\t ");
        }
        System.out.print("0.EXIT\n");
        index = scannerInt.nextInt();
        Category category;
        if (index > 0 && index <= categories.size())
            category = categories.get(--index);
        else if (index == 0)
            return 0;
        else {
            return -1;
        }
        System.out.println(Response.ENTER_SUBCATEGORY);
        String name = scannerStr.nextLine();
        boolean isValid = subcategoriesService.check(name);
        if (!isValid) {
            return -2;
        }
        UUID categoryID = category.getId();
        Subcategory subcategory = new Subcategory(name, categoryID);
        subcategoriesService.add(subcategory);
        return 1;
    }

    public static User signInUser(UsersService usersService) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        System.out.println(Response.ENTER_PHONE_NUMBER);
        String phoneNumber = scannerStr.next();
        User user = usersService.get(phoneNumber);
        if (user == null) {
            System.out.println(Response.USER_NOT_FOUND);
            return null;
        }
        System.out.println(Response.CONFIRM_CODE);
        int code = usersService.sendSMS();
        System.out.println(code);
        int confirmationCode = scannerInt.nextInt();
        while (code != confirmationCode) {
            System.out.println("Enter right sms code");
            System.out.println("1.SEND AGAIN\t 0.EXIT");
            int stepCode = scannerInt.nextInt();
            if (stepCode == 1) {
                code = usersService.sendSMS();
                System.out.println(code);
                confirmationCode = scannerInt.nextInt();
            } else if (stepCode == 0)
                return null;
            else
                System.out.println(Response.ENTER_SWITCH_DEFAULT);
        }
        return user;
    }

    public static User signUpUser(UsersService usersService) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        System.out.println(Response.ENTER_PHONE_NUMBER);
        String phoneNumber = scannerStr.next();
        boolean isValid = usersService.check(phoneNumber);
        if (!isValid)
            return null;
        System.out.println(Response.CONFIRM_CODE);
        int code = usersService.sendSMS();
        System.out.println(code);
        int confirmationCode = scannerInt.nextInt();
        while (code != confirmationCode) {
            System.out.println(Response.CODE_INCORRECT);
            System.out.println("1.SEND AGAIN\t 0.EXIT");
            int stepCode = scannerInt.nextInt();
            if (stepCode == 1) {
                code = usersService.sendSMS();
                System.out.println(code);
                confirmationCode = scannerInt.nextInt();
            } else if (stepCode == 0)
                return null;
            else
                System.out.println(Response.ENTER_SWITCH_DEFAULT);
        }
        User user = new User(phoneNumber);
        usersService.add(user);
        return user;
    }

    public static void menuUser(User user, CardsService cardsService, CategoriesService categoriesService, SubcategoriesService subcategoriesService, LocationsService locationsService, HistoriesService historiesService, ProductsService productsService, CafesService cafesService) {
        Scanner scannerInt = new Scanner(System.in);
        int stepCode = 1;
        while (stepCode != 0) {
            System.out.println("1.ORDER\t 2.CARDS\t 3.LOCATIONS\t 4.HISTORY\t 0.EXIT");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1:
                    int result = menuOrder(user, locationsService, cardsService, categoriesService, subcategoriesService, productsService, cafesService, historiesService);
                    if (result == -1) {
                        System.out.println(Response.ENTER_SWITCH_DEFAULT);
                    } else if (result == 1) {
                        System.out.println(Response.SUCCESS);
                    }
                    break;
                case 2:
                    menuCards(user, cardsService);
                    break;
                case 3:
                    menuLocations(user,locationsService);
                    break;
                case 4:
                    menuHistoryUser(user, historiesService, cardsService, productsService, cafesService);
                    break;
                case 0:
                    break;
                default:
                    System.out.println(Response.ENTER_SWITCH_DEFAULT);
            }
        }
    }

    public static int menuOrder(User user, LocationsService locationsService, CardsService cardsService, CategoriesService categoriesService, SubcategoriesService subcategoriesService, ProductsService productsService, CafesService cafesService, HistoriesService historiesService) {
        Scanner scannerInt = new Scanner(System.in);
        List<Category> categories = categoriesService.getList();
        int index = 0;
        if (categories.size() == 0) {
            System.out.println("There are no categories");
        }
        for (Category category : categories) {
            System.out.print(++index + "." + category.getName() + "\t ");
        }
        System.out.print("0.EXIT\n");
        index = scannerInt.nextInt();
        Category category;
        if (index > 0 && index <= categories.size())
            category = categories.get(--index);
        else if (index == 0)
            return 0;
        else {
            return -1;
        }

        List<Subcategory> subcategories = subcategoriesService.getList(category);
        if (subcategories.size() == 0) {
            System.out.println("There are no subcategories");
        }
        index = 0;
        for (Subcategory subcategory : subcategories) {
            System.out.print(++index + "." + subcategory.getName() + "\t ");
        }
        System.out.print("0.EXIT\n");
        index = scannerInt.nextInt();
        Subcategory subcategory;
        if (index > 0 && index <= subcategories.size())
            subcategory = subcategories.get(--index);
        else if (index == 0)
            return 0;
        else {
            return -1;
        }

        List<Product> products = productsService.getList(subcategory);
        if (products.size() == 0) {
            System.out.println("There are no products");
        }
        index = 0;
        for (Product product : products) {
            Cafe cafe = cafesService.getByID(product.getCafeID());
            double priceWithDiscount = product.getPrice() * (1 - Account.getDiscountPercent() / 100);
            System.out.println(++index + "." + product.getName() + " Cafe: " + cafe.getName() + " Price: " +
                    product.getPrice()  + " With discount: " + priceWithDiscount);
        }
        System.out.println("0.EXIT");
        index = scannerInt.nextInt();
        Product product;
        if (index > 0 && index <= products.size())
            product = products.get(--index);
        else if (index == 0)
            return 0;
        else {
            return -1;
        }

        System.out.println("Enter amount");
        short amount = scannerInt.nextShort();
        if (product.getAmount() < amount) {
            System.out.println("Product amount in cafe is less than " + amount);
            return 0;
        }

        List<Location> locations = locationsService.getList(user);
        if (locations.size() == 0) {
            System.out.println("There are no locations");
        }
        index = 0;
        for (Location location : locations) {
            System.out.println(++index + ". City: " + location.getCity() + " District: " +
                    location.getDistrict() + " Street: " + location.getStreet() + " Home: " +
                    location.getHome());
        }
        System.out.print("0.EXIT\n");
        index = scannerInt.nextInt();
        Location location;
        if (index > 0 && index <= locations.size())
            location = locations.get(--index);
        else if (index == 0)
            return 0;
        else {
            return -1;
        }

        List<Card> cards = cardsService.getList(user);
        if (cards.size() == 0) {
            System.out.println("There are no cards");
        }
        index = 0;
        for (Card card : cards) {
            System.out.println(++index + ".Card name: " + card.getCardNumber() + " Cara number: " +
                    card.getCardNumber() + " Balance: " + card.getBalance());
        }
        index = scannerInt.nextInt();
        Card card;
        if (index > 0 && index <= cards.size())
            card = cards.get(--index);
        else if (index == 0)
            return 0;
        else {
            System.out.println(Response.ENTER_SWITCH_DEFAULT);
            return 0;
        }

        double priceAfterCommission = product.getPrice() * (1 - Account.getCafePercent() / 100);
        double priceWithDiscount = product.getPrice() * (1 - Account.getDiscountPercent() / 100);
        if (priceWithDiscount > card.getBalance()) {
            System.out.println(Response.AMOUNT_OVER);
            return 0;
        }

        Cafe cafe = cafesService.getByID(product.getCafeID());
        Transaction.pay(priceWithDiscount, card, amount);
        Transaction.receive(priceAfterCommission, cafe, amount);
        Transaction.incomeToSystem(product.getPrice(), amount);

        product.setAmount((short) (product.getAmount() - amount));
        HistoryCafe historyCafe = new HistoryCafe(priceAfterCommission, card.getId(), amount, product.getId(), cafe.getId(), priceAfterCommission * amount);
        HistoryUser historyUser = new HistoryUser(priceWithDiscount, card.getId(), amount, product.getId(), user.getId(), priceWithDiscount * amount);
        historiesService.add(historyCafe);
        historiesService.add(historyUser);
        return 1;
    }

    public static void menuCards(User user, CardsService cardsService) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        int stepCode = 1;
        while (stepCode != 0) {
            int index = 0;
            List<Card> cards = cardsService.getList(user);
            if (cards.size() == 0) {
                System.out.println("There are no cards");
            }
            for (Card card : cards) {
                System.out.println(++index + ". Card name: " + card.getName() + " Cara number: " +
                        card.getCardNumber() + " Balance: " + card.getBalance());
            }
            System.out.println("1.ADD CARD\t 2.DELETE CARD\t 0.EXIT");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1:
                    System.out.println(Response.ENTER_CARD_NUMBER);
                    long cardNumber = scannerInt.nextLong();
                    boolean isSuccess = cardsService.check(cardNumber);
                    if (!isSuccess) {
                        System.out.println(Response.CARD_INVALID);
                        System.out.println(Response.REPEAT);
                        break;
                    }
                    System.out.println(Response.CONFIRM_CODE);
                    int code = cardsService.sendSMS();
                    System.out.println(code);
                    int confirmationCode = scannerInt.nextInt();
                    while (code != confirmationCode) {
                        System.out.println(Response.CODE_INCORRECT);
                        System.out.println("1.SEND AGAIN\t 0.EXIT");
                        int stepCode2 = scannerInt.nextInt();
                        if (stepCode2 == 1) {
                            code = cardsService.sendSMS();
                            System.out.println(code);
                            confirmationCode = scannerInt.nextInt();
                        } else if (stepCode2 == 0)
                            break;
                        else
                            System.out.println(Response.ENTER_SWITCH_DEFAULT);
                    }
                    if (code == confirmationCode) {
                        System.out.println(Response.ENTER_BALANCE);
                        double balance = scannerInt.nextDouble();
                        System.out.println(Response.ENTER_CARD_NAME);
                        String cardName = scannerStr.next();
                        System.out.println(Response.SUCCESS);
                        Card newCard = new Card(cardName, cardNumber, balance, user.getId());
                        cardsService.add(newCard);
                    }
                    break;
                case 2:
                    System.out.println("Enter index");
                    index = scannerInt.nextInt();
                    System.out.println(Response.SUCCESS);
                    Card card = cards.get(--index);
                    card.setActive(false);
                    break;
                case 0:
                    break;
                default:
                    System.out.println(Response.ENTER_SWITCH_DEFAULT);
            }
        }
    }

    public static void menuLocations(User user, LocationsService locationsService) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        int stepCode = 1;
        while (stepCode != 0) {
            int index = 0;
            List<Location> locations = locationsService.getList(user);
            if (locations.size() == 0) {
                System.out.println("There are no locations");
            }
            for (Location location : locations) {
                System.out.println(++index + ". City: " + location.getCity() + " District: " +
                        location.getDistrict() + " Street: " + location.getStreet() + " Home: " +
                        location.getHome());
            }
            System.out.println("1.ADD LOCATION\t 2.DELETE LOCATION\t 0.EXIT");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1:
                    System.out.println("Enter city");
                    String city = scannerStr.nextLine();
                    System.out.println("Enter district");
                    String district = scannerStr.nextLine();
                    System.out.println("Enter street");
                    String street = scannerStr.nextLine();
                    System.out.println("Enter home");
                    String home = scannerStr.nextLine();
                    boolean found = false;
                    for (Location location : locations) {
                        if (location.getCity().equals(city) && location.getDistrict().equals(district)
                                && location.getStreet().equals(street) && location.getHome().equals(home)) {
                            System.out.println(Response.CARD_INVALID);
                            System.out.println(Response.REPEAT);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        Location newLocation = new Location(city, district, street, home, user.getId());
                        locationsService.add(newLocation);
                    }
                    break;
                case 2:
                    System.out.println("Enter index");
                    index = scannerInt.nextInt();
                    System.out.println(Response.SUCCESS);
                    Location location = locations.get(--index);
                    location.setActive(false);
                    break;
                case 0:
                    break;
                default:
                    System.out.println(Response.ENTER_SWITCH_DEFAULT);
            }
        }
    }

    public static void menuHistoryUser(User user, HistoriesService historiesService, CardsService cardsService, ProductsService productsService, CafesService cafesService) {
        List<HistoryUser> historyUsers = historiesService.getHistories(user);
        int index = 0;
        if (historyUsers.size() == 0) {
            System.out.println("There are no histories");
        }
        for (HistoryUser historyUser : historyUsers) {
            Card card = cardsService.getByID(historyUser.getCardID());
            Product product = productsService.getByID(historyUser.getProductID());
            Cafe cafe = cafesService.getByID(product.getCafeID());
            System.out.println(++index + ". Card name: " + card.getName() + " Card number: " +
                    card.getCardNumber() + " Product name: " + product.getName() + " Cafe name: " +
                    cafe.getName() + " Price: " + historyUser.getPrice() + " Amount: " +
                    historyUser.getAmount() + " Total: " + historyUser.getTotalPrice());
        }
    }

    public static Cafe signInCafe(CafesService cafesService) {
        Scanner scannerStr = new Scanner(System.in);
        System.out.println(Response.ENTER_USERNAME);
        String username = scannerStr.next();
        System.out.println(Response.ENTER_PASSWORD);
        String password = scannerStr.next();
        return cafesService.get(username, password);
    }

    public static Cafe signUpCafe(CafesService cafesService) {
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerLine = new Scanner(System.in);
        System.out.println(Response.ENTER_USERNAME);
        String username = scannerStr.next();
        boolean isValid = cafesService.checkUsername(username);
        if (!isValid) {
            System.out.println("There is already cafe with this username");
            return null;
        }
        System.out.println(Response.ENTER_PASSWORD);
        String password = scannerStr.next();
        System.out.println("Enter name");
        String name = scannerLine.nextLine();
        isValid = cafesService.check(name);
        if (!isValid) {
            System.out.println("There is already cafe with this name");
            return null;
        }
        System.out.println(Response.ENTER_PHONE_NUMBER);
        String phoneNumber = scannerLine.nextLine();
        Cafe cafe = new Cafe(name, username, password, phoneNumber);
        cafesService.add(cafe);
        return cafe;
    }

    public static void menuCafe(Cafe cafe, CategoriesService categoriesService, SubcategoriesService subcategoriesService, ProductsService productsService, HistoriesService historiesService, CardsService cardsService) {
        Scanner scannerInt = new Scanner(System.in);
        int stepCode = 1;
        while (stepCode != 0) {
            System.out.println("1.PRODUCTS\t 2.BALANCE\t 3.HISTORY\t 0.EXIT");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1:
                    menuProducts(cafe, categoriesService, subcategoriesService, productsService);
                    break;
                case 2:
                    System.out.println("Balance: " + cafe.getBalance());
                    break;
                case 3:
                    menuHistoryCafe(cafe, historiesService, cardsService, productsService);
                    break;
                case 0:
                    break;
                default:
                    System.out.println(Response.ENTER_SWITCH_DEFAULT);
            }
        }
    }

    public static void menuProducts(Cafe cafe, CategoriesService categoriesService, SubcategoriesService subcategoriesService, ProductsService productsService) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        int stepCode = 1;
        while (stepCode != 0) {
            int index = 0;
            List<Product> products = productsService.getList(cafe);
            if (products.size() == 0) {
                System.out.println("There are no products");
            }
            for (Product product : products) {
                String subcategoryName = subcategoriesService.getName(product.getSubcategoryID());
                System.out.println(++index + ". Name: " + product.getName() + " Subcategory: " +
                        subcategoryName + " Amount: " + product.getAmount() + " Price: " +
                        product.getPrice());
            }
            System.out.println("1.ADD PRODUCT\t 2.DELETE PRODUCT\t 0.EXIT");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1:
                    List<Category> categories = categoriesService.getList();
                    index = 0;
                    for (Category category : categories) {
                        System.out.print(++index + "." + category.getName() + "\t ");
                    }
                    System.out.print("0.EXIT\n");
                    index = scannerInt.nextInt();
                    Category category;
                    if (index > 0 && index <= categories.size())
                        category = categories.get(--index);
                    else if (index == 0)
                        break;
                    else {
                        System.out.println(Response.ENTER_SWITCH_DEFAULT);
                        break;
                    }

                    List<Subcategory> subcategories = subcategoriesService.getList(category);
                    index = 0;
                    for (Subcategory subcategory : subcategories) {
                        System.out.print(++index + "." + subcategory.getName() + "\t ");
                    }
                    System.out.print("0.EXIT\n");
                    index = scannerInt.nextInt();
                    Subcategory subcategory;
                    if (index > 0 && index <= subcategories.size())
                        subcategory = subcategories.get(--index);
                    else if (index == 0)
                        break;
                    else {
                        System.out.println(Response.ENTER_SWITCH_DEFAULT);
                        break;
                    }

                    System.out.println("Enter product name");
                    String name = scannerStr.nextLine();
                    boolean isValid = productsService.check(name, cafe.getId());
                    if (!isValid) {
                        System.out.println("There is already product with this name");
                        break;
                    }
                    System.out.println("Enter price");
                    double price = scannerInt.nextDouble();
                    System.out.println("Enter amount");
                    short amount = scannerInt.nextShort();
                    Product newProduct = new Product(name, price, amount, subcategory.getId(), cafe.getId());
                    productsService.add(newProduct);
                    System.out.println(Response.SUCCESS);
                    break;
                case 2:
                    System.out.println("Enter index");
                    index = scannerInt.nextInt();
                    System.out.println(Response.SUCCESS);
                    Product product = products.get(--index);
                    product.setActive(false);
                    break;
                case 0:
                    break;
                default:
                    System.out.println(Response.ENTER_SWITCH_DEFAULT);
            }
        }
    }

    public static void menuHistoryCafe(Cafe cafe, HistoriesService historiesService, CardsService cardsService, ProductsService productsService) {
        List<HistoryCafe> historyCafes = historiesService.getHistories(cafe);
        int index = 0;
        if (historyCafes.size() == 0) {
            System.out.println("There are no histories");
        }
        for (HistoryCafe historyCafe : historyCafes) {
            Card card = cardsService.getByID(historyCafe.getCardID());
            Product product = productsService.getByID(historyCafe.getProductID());
            System.out.println(++index + " Product name: " + product.getName() + " Price: " + historyCafe.getPrice() +
                    " Amount: " + historyCafe.getAmount() + " Card number: " + card.getCardNumber() + " Total: " +
                    historyCafe.getTotalPrice());
        }
    }
}
