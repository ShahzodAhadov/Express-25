package uz.pdp.model.base;


import java.util.UUID;

public class HistoryModel {
    protected short amount;
    protected double price;
    protected UUID cardID;
    protected UUID productID;
    protected final UUID id;
    protected double totalPrice;

    {
        id = UUID.randomUUID();
    }

    public HistoryModel(double price, UUID cardID, short amount, UUID productID, double totalPrice) {
        this.price = price;
        this.cardID = cardID;
        this.productID = productID;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public HistoryModel() {}

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

    public UUID getCardID() {
        return cardID;
    }

    public void setCardID(UUID cardID) {
        this.cardID = cardID;
    }

    public UUID getProductID() {
        return productID;
    }

    public void setProductID(UUID productID) {
        this.productID = productID;
    }

    public UUID getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
