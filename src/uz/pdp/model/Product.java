package uz.pdp.model;

import uz.pdp.model.base.BaseCategory;
import uz.pdp.model.base.BaseModel;

import java.util.UUID;

public class Product extends BaseCategory {
    private UUID subcategoryID;
    private UUID cafeID;
    protected short amount;
    protected double price;

    public Product(String name, double price, short amount, UUID subcategoryID, UUID cafeID) {
        super(name);
        this.price = price;
        this.amount = amount;
        this.cafeID = cafeID;
        this.subcategoryID = subcategoryID;
    }

    public UUID getSubcategoryID() {
        return subcategoryID;
    }

    public void setSubcategoryID(UUID subcategoryID) {
        this.subcategoryID = subcategoryID;
    }

    public UUID getCafeID() {
        return cafeID;
    }

    public void setCafeID(UUID cafeID) {
        this.cafeID = cafeID;
    }

    public short getAmount() {
        return amount;
    }

    public void setAmount(short amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
