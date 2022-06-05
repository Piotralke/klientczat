package com.example.klientczat;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public PersonController personController;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField loginText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField surnameText;

    @FXML void postRegister(Event event) {
        Person person = new Person(nameText.getText(),surnameText.getText(),loginText.getText(),passwordText.getText());
        try {
            personController.register(person);
        } catch (IOException e) {
            errorLabel.setText("Taki login juz istnieje");
        }
    }

    @FXML
    public void goBack(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
