package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static final String API_KEY ="7c40f938f99d4be5b3c7e274061fb102";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("News Filter");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }
}



/*
ApiController api = new ApiController();
        api.getHeadlines(API_KEY);
        System.out.println("main done");
 */