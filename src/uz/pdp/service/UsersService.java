package uz.pdp.service;

import uz.pdp.model.Card;
import uz.pdp.model.User;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsersService implements BaseService<User, List<User>, Card, String, UUID> {
    private List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public User get(String phoneNumber) {
        for (User user : users) {
            if (user.getPhoneNumber().equals(phoneNumber))
                return user;
        }
        return null;
    }

    @Override
    public int sendSMS() {
        return (int) (Math.random() * 90000 + 10000);
    }

    @Override
    public List<User> getList(Card card) {
        return null;
    }

    @Override
    public List<User> getList() {
        return null;
    }

    @Override
    public boolean check(String phoneNumber) {
        for (User user : users) {
            if (user.getPhoneNumber().equals(phoneNumber))
                return false;
        }
        return true;
    }

    @Override
    public User get(String d1, String d2) {
        return null;
    }

    @Override
    public User getByID(UUID uuid) {
        return null;
    }
}
