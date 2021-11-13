package uz.pdp.model;

public class Account {
    private static double discountPercent;
    private static double cafePercent;
    private static double systemBalance;

    public static double getDiscountPercent() {
        return discountPercent;
    }

    public static void setDiscountPercent(double discountPercent) {
        Account.discountPercent = discountPercent;
    }

    public static double getCafePercent() {
        return cafePercent;
    }

    public static void setCafePercent(double cafePercent) {
        Account.cafePercent = cafePercent;
    }

    public static double getSystemBalance() {
        return systemBalance;
    }

    public static void setSystemBalance(double systemBalance) {
        Account.systemBalance = systemBalance;
    }

}
