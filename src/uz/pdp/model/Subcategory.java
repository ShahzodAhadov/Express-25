package uz.pdp.model;

import java.util.UUID;

public class Subcategory extends Category {
    protected UUID categoryID;

    public Subcategory(String name, UUID categoryID) {
        super.name = name;
        this.categoryID = categoryID;
    }

    public Subcategory() {}

    public UUID getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(UUID categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
    }
}
