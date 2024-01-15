package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.PasswordCategory;
import sample.models.PasswordCollectionManager;

import java.net.URL;
import java.util.ResourceBundle;


public class CategoryController implements Initializable {
    PasswordCollectionManager pcm;

    @FXML
    private TextField categoryName;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pcm = Main.pcm;

        if (pcm.selectedCategory != null){
            categoryName.setText(pcm.selectedCategory.getName());
        }

        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (pcm.selectedCategory != null) {
                    for (int i = 0; i <pcm.categories.size(); i++) {
                        if(pcm.categories.get(i).getName().equals(pcm.selectedCategory.getName())){
                            pcm.categories.get(i).setName(categoryName.getText());
                        }
                    }
                }
                else{
                    pcm.categories.add(new PasswordCategory(categoryName.getText()));
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
