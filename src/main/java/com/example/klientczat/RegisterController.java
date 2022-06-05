package com.example.klientczat;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML
    private Button button;
    private boolean loginCheck=false;
    private boolean passwordCheck=false;
    private boolean nameCheck=false;
    private boolean surnameCheck=false;

    private void check(){
        button.setDisable(!loginCheck || !passwordCheck || !nameCheck || !surnameCheck);
    }

    public void init(){
        check();
        loginText.textProperty().addListener((observable, oldValue, newValue) -> {
            loginCheck=!newValue.trim().isEmpty();
            check();
        });
        passwordText.textProperty().addListener((observable, oldValue, newValue) -> {
            passwordCheck=!newValue.trim().isEmpty();
            check();
        });
        nameText.textProperty().addListener((observable, oldValue, newValue) -> {
            nameCheck=!newValue.trim().isEmpty();
            check();
        });
        surnameText.textProperty().addListener((observable, oldValue, newValue) -> {
            surnameCheck=!newValue.trim().isEmpty();
            check();
        });
    }

    @FXML void postRegister(Event event) throws IOException {
        if(personController.findByLogin(loginText.getText())==null){
            errorLabel.setText("");
            Person person = new Person(nameText.getText(),surnameText.getText(),loginText.getText(),passwordText.getText());
            personController.register(person);
            goBack(event);
        }else{
            errorLabel.setText("Uzytkownik o takim loginie juz istnieje!");
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
