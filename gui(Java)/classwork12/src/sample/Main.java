package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.models.PasswordCollectionManager;

import java.net.URL;

public class Main extends Application {
    public static URL passwordView;
    public static URL categoryView;
    public static URL register;
    public static URL sample;
    public static PasswordCollectionManager pcm;
    @Override
    public void start(Stage primaryStage) throws Exception{
        pcm = new PasswordCollectionManager(false);
        categoryView = getClass().getResource("views/categoryView.fxml");
        passwordView = getClass().getResource("views/passwordView.fxml");
        register = getClass().getResource("views/register.fxml");
        sample = getClass().getResource("views/sample.fxml");
        Parent root = FXMLLoader.load(getClass().getResource("views/register.fxml"));
        primaryStage.setTitle("Hello World");
        Scene sc = new Scene(root, 1000, 300);
        sc.getStylesheets().add( getClass().getResource("views/styles.css").toString());
        primaryStage.setScene(sc);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
