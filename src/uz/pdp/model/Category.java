package uz.pdp.model;

import uz.pdp.model.base.BaseCategory;

import java.util.UUID;

public class Category extends BaseCategory {

    public Category() {}

    public Category(String name) {
        super(name);
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
