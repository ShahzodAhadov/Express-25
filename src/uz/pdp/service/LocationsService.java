package uz.pdp.service;

import uz.pdp.model.Card;
import uz.pdp.model.Location;
import uz.pdp.model.User;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LocationsService implements BaseService<Location, List<Location>, User, String, UUID> {
    private List<Location> locations = new ArrayList<>();

    @Override
    public void add(Location location) {
        locations.add(location);
    }

    @Override
    public int sendSMS() {
        return 0;
    }

    @Override
    public List<Location> getList(User user) {
        UUID userID = user.getId();
        List<Location> myLocations = new ArrayList<>();
        for (Location location : locations) {
            if (location.getUserID() == userID && location.isActive()) {
                myLocations.add(location);
            }
        }
        return myLocations;
    }

    @Override
    public List<Location> getList() {
        return null;
    }

    @Override
    public boolean check(String s) {
        return false;
    }

    @Override
    public Location get(String s) {
        return null;
    }

    @Override
    public Location get(String d1, String d2) {
        return null;
    }

    @Override
    public Location getByID(UUID uuid) {
        return null;
    }
}
