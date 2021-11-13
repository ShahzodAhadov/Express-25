package uz.pdp.service;

import uz.pdp.model.Category;
import uz.pdp.model.Subcategory;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoriesService implements BaseService<Category, List<Category>, Subcategory, String, UUID> {
    private List<Category> categories = new ArrayList<>();

    @Override
    public void add(Category category) {
        categories.add(category);
    }

    @Override
    public List<Category> getList(Subcategory subcategory) {
        return null;
    }

    @Override
    public List<Category> getList() {
        return categories;
    }

    @Override
    public int sendSMS() {
        return 0;
    }

    @Override
    public boolean check(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name))
                return false;
        }
        return true;
    }

    @Override
    public Category get(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name))
                return category;
        }
        return null;
    }

    @Override
    public Category get(String d1, String d2) {
        return null;
    }

    @Override
    public Category getByID(UUID uuid) {
        return null;
    }
}
