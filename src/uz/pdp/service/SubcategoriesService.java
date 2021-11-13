package uz.pdp.service;

import uz.pdp.model.Category;
import uz.pdp.model.Subcategory;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SubcategoriesService implements BaseService<Subcategory, List<Subcategory>, Category, String, UUID> {
    private List<Subcategory> subcategories = new ArrayList<>();

    @Override
    public void add(Subcategory subcategory) {
        subcategories.add(subcategory);
    }

    @Override
    public int sendSMS() {
        return 0;
    }

    @Override
    public List<Subcategory> getList(Category category) {
        UUID categoryID = category.getId();
        List<Subcategory> mySubcategories = new ArrayList<>();
        for (Subcategory subcategory : subcategories) {
            if (subcategory.getCategoryID() == categoryID)
                mySubcategories.add(subcategory);
        }
        return mySubcategories;
    }

    @Override
    public List<Subcategory> getList() {
        return subcategories;
    }

    @Override
    public boolean check(String name) {
        for (Subcategory subcategory : subcategories) {
            if (subcategory.getName().equals(name))
                return false;
        }
        return true;
    }

    @Override
    public Subcategory get(String s) {
        return null;
    }

    @Override
    public Subcategory get(String d1, String d2) {
        return null;
    }

    @Override
    public Subcategory getByID(UUID uuid) {
        return null;
    }

    public String getName(UUID id) {
        for (Subcategory subcategory : subcategories) {
            if (subcategory.getId() == id && subcategory.isActive()) {
                return subcategory.getName();
            }
        }
        return null;
    }
}
