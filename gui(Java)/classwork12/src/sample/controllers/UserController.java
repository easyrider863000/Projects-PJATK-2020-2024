package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.models.CryptWithMD5;
import sample.models.PasswordCollectionManager;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    PasswordCollectionManager pcm;

    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private Button chooseBtn;
    @FXML
    private Button chooseNewBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pcm = Main.pcm;


        chooseNewBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = new FileChooser().showOpenDialog(null);
                if (file != null) {
                    pcm = new PasswordCollectionManager(true);
                    pcm.file = file;
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(Main.sample);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Passwords");
                    stage.setScene(new Scene(root, 1000, 300));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                }
            }
        });


        chooseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                File file = new FileChooser().showOpenDialog(null);
                if (file != null) {
                    pcm = load(file);
                    if (pcm != null) {
                        if(pcm.UserLogin.equals(login)&&pcm.UserHashPassword.equals(CryptWithMD5.cryptWithMD5(password.getText()))){
                            System.out.println("Ok");
                        }
                    }
                }
            }
        });
    }

    private PasswordCollectionManager load(File file){
        PasswordCollectionManager pcmn = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {
            pcmn=((PasswordCollectionManager)ois.readObject());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return pcmn;
    }
}
