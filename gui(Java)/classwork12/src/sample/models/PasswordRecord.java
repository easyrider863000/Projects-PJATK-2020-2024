package sample.models;

import java.io.Serializable;

public class PasswordRecord implements Serializable {
    private String name;
    private String password;
    private String login;
    private String webLink;
    private PasswordCategory category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public PasswordCategory getCategory() {
        return category;
    }

    public void setCategory(PasswordCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "PasswordRecord{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", webLink='" + webLink + '\'' +
                ", category=" + category.toString() +
                '}';
    }

    public PasswordRecord(String name, String password, String login, String webLink, PasswordCategory category) {
        this.name = name;
        this.password = password;
        this.login = login;
        this.webLink = webLink;
        this.category = category;
    }
}
