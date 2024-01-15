package sample.models;

import java.io.Serializable;

public class PasswordCategory implements Serializable {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public PasswordCategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
