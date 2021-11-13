package uz.pdp.model.base;

import java.util.UUID;

public abstract class BaseModel {
    protected String username;
    protected String password;
    protected String phoneNumber;
    protected double balance;
    protected final UUID id;
    protected String name;
    protected boolean isActive = true;

    {
        id = UUID.randomUUID();
    }

    public BaseModel() {}

    public BaseModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public BaseModel(String phoneNumber) { // for user
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
