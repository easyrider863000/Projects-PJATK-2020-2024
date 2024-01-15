package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.PasswordCategory;
import sample.models.PasswordCollectionManager;
import sample.models.PasswordRecord;

import java.net.URL;
import java.util.ResourceBundle;

public class PasswordController implements Initializable {
    PasswordCollectionManager pcm;

    @FXML
    private TextField name;
    @FXML
    private TextField password;
    @FXML
    private TextField login;
    @FXML
    private TextField webLink;
    @FXML
    private ChoiceBox<PasswordCategory> category;

    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pcm = Main.pcm;

        if (pcm.selectedPassword != null) {
            name.setText(pcm.selectedPassword.getName());
            password.setText(pcm.selectedPassword.getPassword());
            login.setText(pcm.selectedPassword.getLogin());
            webLink.setText(pcm.selectedPassword.getWebLink());
            category.setItems(pcm.categories);
            category.setValue(pcm.selectedPassword.getCategory());
        } else {
            category.setItems(pcm.categories);
        }



        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (pcm.selectedPassword != null) {
                    for (int i = 0; i <pcm.passwords.size(); i++) {
                        if(pcm.passwords.get(i).equals(pcm.selectedPassword)){
                            pcm.passwords.get(i).setName(name.getText());
                            pcm.passwords.get(i).setPassword(password.getText());
                            pcm.passwords.get(i).setLogin(login.getText());
                            pcm.passwords.get(i).setWebLink(webLink.getText());
                            pcm.passwords.get(i).setCategory(category.getValue());
                        }
                    }
                }
                else{
                    pcm.passwords.add(new PasswordRecord(name.getText(), password.getText(), login.getText(), webLink.getText(), category.getValue()));
                }
                Stage stage = (Stage) saveBtn.getScene().getWindow();
                stage.close();
            }
        });
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage) cancelBtn.getScene().getWindow();
                stage.close();
            }
        });
    }
}
