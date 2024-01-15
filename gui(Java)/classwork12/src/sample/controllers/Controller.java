package sample.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.models.PasswordCategory;
import sample.models.PasswordCollectionManager;
import sample.models.PasswordRecord;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    static PasswordCollectionManager pcm;
    @FXML
    private TableView<PasswordRecord> passwords;

    @FXML
    private TableColumn<PasswordRecord, String> name;
    @FXML
    private TableColumn<PasswordRecord, String> password;
    @FXML
    private TableColumn<PasswordRecord, String> login;
    @FXML
    private TableColumn<PasswordRecord, String> weblink;
    @FXML
    private TableColumn<PasswordCategory, String> category;

    @FXML
    private Button addPasswordBtn;
    @FXML
    private Button editPasswordBtn;
    @FXML
    private Button removePasswordBtn;
    @FXML
    private Button saveBtn;

    @FXML
    public TableView<PasswordCategory> categories;

    @FXML
    private TableColumn<PasswordCategory, String> categoryName;

    @FXML
    private Button addCategoryBtn;
    @FXML
    private Button editCategoryBtn;
    @FXML
    private Button removeCategoryBtn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pcm = Main.pcm;


        name.setCellValueFactory(new PropertyValueFactory<PasswordRecord, String>("name"));
        password.setCellValueFactory(new PropertyValueFactory<PasswordRecord, String>("password"));
        login.setCellValueFactory(new PropertyValueFactory<PasswordRecord, String>("login"));
        weblink.setCellValueFactory(new PropertyValueFactory<PasswordRecord, String>("webLink"));
        category.setCellValueFactory(new PropertyValueFactory<PasswordCategory, String>("category"));
        passwords.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        passwords.setItems(pcm.getPasswords());

        categoryName.setCellValueFactory(new PropertyValueFactory<PasswordCategory, String>("name"));
        categories.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        categories.setItems(pcm.getCategories());
        categories.setEditable(true);

        editPasswordBtn.disableProperty().bind(Bindings.isEmpty(passwords.getSelectionModel().getSelectedItems()));
        removePasswordBtn.disableProperty().bind(Bindings.isEmpty(passwords.getSelectionModel().getSelectedItems()));
        editCategoryBtn.disableProperty().bind(Bindings.isEmpty(categories.getSelectionModel().getSelectedItems()));
        removeCategoryBtn.disableProperty().bind(Bindings.isEmpty(categories.getSelectionModel().getSelectedItems()));


        removePasswordBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                pcm.removePasswords(passwords.getSelectionModel().getSelectedItems());
                passwords.setItems(pcm.getPasswords());
            }
        });
        removeCategoryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                pcm.removeCategories(categories.getSelectionModel().getSelectedItems());
                categories.setItems(pcm.getCategories());
                passwords.setItems(pcm.getPasswords());
            }
        });

        categories.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                pcm.filterByCategories(categories.getSelectionModel().getSelectedItems());
                passwords.setItems(pcm.getPasswords());
            }
        });
        addCategoryBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                pcm.selectedCategory = null;
                Parent root = null;
                try {
                    root = FXMLLoader.load(Main.categoryView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("Add new Category");
                stage.setScene(new Scene(root, 600, 300));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                categories.setItems(pcm.getCategories());
            }
        });
        editCategoryBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                pcm.selectedCategory = categories.getSelectionModel().getSelectedItems().get(0);
                Parent root = null;
                try {
                    root = FXMLLoader.load(Main.categoryView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("Add/Edit new Category");
                stage.setScene(new Scene(root, 600, 300));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                categories.setItems(pcm.getCategories());
            }
        });





        addPasswordBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                pcm.selectedPassword = null;
                Parent root = null;
                try {
                    root = FXMLLoader.load(Main.passwordView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("Add new password");
                stage.setScene(new Scene(root, 600, 300));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                passwords.setItems(pcm.getPasswords());
            }
        });
        editPasswordBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                pcm.selectedPassword = passwords.getSelectionModel().getSelectedItems().get(0);
                Parent root = null;
                try {
                    root = FXMLLoader.load(Main.passwordView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("Add/Edit new Category");
                stage.setScene(new Scene(root, 600, 300));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                passwords.setItems(pcm.getPasswords());
            }
        });

        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //writetofile


                Stage stage = (Stage) saveBtn.getScene().getWindow();
                stage.close();
            }
        });
        //isShowCategory.setCellValueFactory();
    }
}