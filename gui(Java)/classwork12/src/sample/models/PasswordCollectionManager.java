package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PasswordCollectionManager implements Serializable {
    public ObservableList<PasswordRecord> filtersPasswords;
    public ObservableList<PasswordRecord> passwords;
    public ObservableList<PasswordCategory> categories;
    public List<String> filtersCategory;
    public PasswordRecord selectedPassword;
    public PasswordCategory selectedCategory;
    public String UserLogin;
    public String UserHashPassword;
    public File file;


    public void PasswordsToHH() {
        for (PasswordRecord password : passwords) {
            password.setPassword(Caesar.encrypt(password.getPassword()));
        }
        for (PasswordRecord filtersPassword : filtersPasswords) {
            filtersPassword.setPassword(Caesar.encrypt(filtersPassword.getPassword()));
        }
        selectedPassword.setPassword(Caesar.encrypt(selectedPassword.getPassword()));
    }
    public void HHToPasswords() {
        for (PasswordRecord password : passwords) {
            password.setPassword(Caesar.decrypt(password.getPassword()));
        }
        for (PasswordRecord filtersPassword : filtersPasswords) {
            filtersPassword.setPassword(Caesar.decrypt(filtersPassword.getPassword()));
        }
        selectedPassword.setPassword(Caesar.decrypt(selectedPassword.getPassword()));
    }

    public ObservableList<PasswordRecord> getPasswords() {
        if(filtersCategory.isEmpty()){return passwords;}
        filtersPasswords = FXCollections.observableArrayList();
        filtersPasswords.addAll(passwords);
        for (PasswordRecord password : passwords) {
            boolean tmp = true;
            for (String s : filtersCategory) {
                if (s.equals(password.getCategory().getName())) {
                    tmp = false;
                    break;
                }
            }
            if (tmp) {
                filtersPasswords.remove(password);
            }
        }
        return filtersPasswords;
    }
    public ObservableList<PasswordCategory> getCategories() {
        return categories;
    }
    public void filterByCategories(ObservableList<PasswordCategory> list){
        List<String> list2 = new ArrayList<>();
        for (PasswordCategory passwordCategory : list) {
            if (passwordCategory != null) {
                list2.add(passwordCategory.getName());
            }
        }
        filtersCategory = new ArrayList<>(list2);
    }
    public void removePasswords(ObservableList<PasswordRecord> list){
        ObservableList<PasswordRecord> tmpPasswords = FXCollections.observableArrayList();
        tmpPasswords.addAll(passwords);
        for (PasswordRecord password : passwords) {
            boolean tmp = true;
            for (PasswordRecord passwordRecord : list) {
                if (passwordRecord.equals(password)) {
                    tmp = false;
                    break;
                }
            }
            if (!tmp) {
                tmpPasswords.remove(password);
            }
        }
        passwords = tmpPasswords;
    }
    public void removeCategories(ObservableList<PasswordCategory> list) {
        ObservableList<PasswordCategory> tmpCategories = FXCollections.observableArrayList();
        tmpCategories.addAll(categories);
        for (PasswordCategory category : categories) {
            boolean tmp = true;
            for (PasswordCategory passwordCategory : list) {
                if (passwordCategory.equals(category)) {
                    tmp = false;
                    break;
                }
            }
            if (!tmp) {
                ObservableList<PasswordRecord> tmp2 = getPasswordsByCategory(category.getName());
                for (PasswordRecord passwordRecord : tmp2) {
                    passwords.remove(passwordRecord);
                }
                tmpCategories.remove(category);
            }
        }
        categories = tmpCategories;
    }
    public ObservableList<PasswordRecord> getPasswordsByCategory(String categry){
        ObservableList<PasswordRecord> tmp = FXCollections.observableArrayList();
        for (PasswordRecord password : passwords) {
            if (password.getCategory().getName().equals(categry)) {
                tmp.add(password);
            }
        }
        return tmp;
    }

    public PasswordCollectionManager(boolean isEmpty){
        passwords = FXCollections.observableArrayList();
        categories = FXCollections.observableArrayList();
        filtersCategory = new ArrayList<>();
        if (!isEmpty) {
            categories.add(new PasswordCategory("category1"));
            categories.add(new PasswordCategory("category2"));
            categories.add(new PasswordCategory("category3"));

            passwords.add(new PasswordRecord("password1", "secret_ps", "login228", "https://ok.pl", getCategories().get(0)));
            passwords.add(new PasswordRecord("password2", "secret_p2", "login222", "https://2k.pl", getCategories().get(1)));
            passwords.add(new PasswordRecord("password2", "secret_p1", "login222", "https://2k.pl", getCategories().get(1)));
        }
        filtersPasswords = passwords;
    }

}
