package uz.pdp.model;

import uz.pdp.model.base.HistoryModel;

import java.util.UUID;

public class HistoryUser extends HistoryModel {
    private UUID userID;

    public HistoryUser(double price, UUID cardID, short amount, UUID productID, UUID userID1, double totalPrice) {
        super(price, cardID, amount, productID, totalPrice);
        this.userID = userID1;
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

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }
}
