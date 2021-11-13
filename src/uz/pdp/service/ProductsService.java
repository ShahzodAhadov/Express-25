package uz.pdp.service;

import uz.pdp.model.*;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductsService implements BaseService<Product, List<Product>, Cafe, String, UUID> {
    private List<Product> products = new ArrayList<>();

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public int sendSMS() {
        return 0;
    }

    @Override
    public List<Product> getList(Cafe cafe) {
        UUID cafeID = cafe.getId();
        List<Product> myProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCafeID() == cafeID && product.isActive()) {
                myProducts.add(product);
            }
        }
        return myProducts;
    }

    @Override
    public List<Product> getList() {
        return null;
    }

    @Override
    public boolean check(String name) {
        return false;
    }

    @Override
    public Product get(String s) {
        return null;
    }

    @Override
    public Product get(String d1, String d2) {
        return null;
    }

    @Override
    public Product getByID(UUID id) {
        for (Product product : products) {
            if (product.getId().equals(id))
                return product;
        }
        return null;
    }

    public boolean check(String name, UUID cafeID) {
        for (Product product : products) {
            if (product.getCafeID() == cafeID) {
                if (product.getName().equals(name))
                    return false;
            }
        }
        return true;
    }

    public List<Product> getList(Subcategory subcategory) {
        UUID subcategoryID = subcategory.getId();
        List<Product> myProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getSubcategoryID() == subcategoryID && product.isActive()) {
                myProducts.add(product);
            }
        }
        return myProducts;
    }
}
