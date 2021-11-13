package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

import java.util.UUID;

public class User extends BaseModel {

    public User(String phoneNumber) {
        super(phoneNumber);
    }

    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        super.setPhoneNumber(phoneNumber);
    }

    @Override
    public UUID getId() {
        return super.getId();
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
