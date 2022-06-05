package com.example.klientczat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.klientczat.ChatController.refreshConv;
import static com.example.klientczat.ConvController.refresh;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Czat Internetowy!");
        stage.setHeight(600);
        stage.setWidth(400);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop(){
        refresh=false;
        refreshConv=false;
    }

    public static void main(String[] args) {
        launch();
    }
}