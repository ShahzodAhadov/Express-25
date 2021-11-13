package uz.pdp.model.base;

import java.util.UUID;

public class BaseCategory {
    protected final UUID id;
    protected String name;
    protected boolean isActive = true;

    {
        id = UUID.randomUUID();
    }

    public BaseCategory() {}

    public BaseCategory(String name) {
        this.name = name;
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
