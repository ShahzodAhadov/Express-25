package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

public class Admin extends BaseModel {

    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }
}
