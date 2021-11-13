package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

import java.util.UUID;

public class Location extends BaseModel {
    private UUID userID;
    private String city;
    private String district;
    private String street;
    private String home;

    public Location(String city, String district, String street, String home, UUID userID) {
        this.city = city;
        this.district = district;
        this.street = street;
        this.home = home;
        this.userID = userID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
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
