package uz.pdp.model;

import uz.pdp.model.base.HistoryModel;

import java.util.UUID;

public class HistoryCafe extends HistoryModel {
    private UUID cafeID;

    public HistoryCafe(double price, UUID cardID, short amount, UUID productID, UUID cafeID, double totalPrice) {
        super(price, cardID, amount, productID, totalPrice);
        this.cafeID = cafeID;
    }

    public UUID getCafeID() {
        return cafeID;
    }

    public void setCafeID(UUID cafeID) {
        this.cafeID = cafeID;
    }

    @Override
    public short getAmount() {
        return super.getAmount();
    }

    @Override
    public void setAmount(short amount) {
        super.setAmount(amount);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    @Override
    public UUID getCardID() {
        return super.getCardID();
    }

    @Override
    public void setCardID(UUID cardID) {
        super.setCardID(cardID);
    }

    @Override
    public UUID getProductID() {
        return super.getProductID();
    }

    @Override
    public void setProductID(UUID productID) {
        super.setProductID(productID);
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    @Override
    public double getTotalPrice() {
        return super.getTotalPrice();
    }

    @Override
    public void setTotalPrice(double totalPrice) {
        super.setTotalPrice(totalPrice);
    }
}
