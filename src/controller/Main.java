package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.StageManager;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
        Parent load = fxmlLoader.load();
        primaryStage.setTitle("loginPage");
        primaryStage.setScene(new Scene(load));
        primaryStage.show();
        StageManager.STAGE.put("Login",primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
