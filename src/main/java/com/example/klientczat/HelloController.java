package com.example.klientczat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    static PersonController personController = new PersonController();
    static{
        personController.start();
    }

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField loginText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label errorLabel;

    @FXML
    void login(ActionEvent event) throws IOException{
        Person person = PersonController.login(loginText.getText(),passwordText.getText());
        if(person!=null){
            errorLabel.setText("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
            root=loader.load();
            ChatController chatController = loader.getController();
            chatController.init(person,personController);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        }else{
            errorLabel.setText("Niepoprawne dane dostępowe");
        }
    }

    @FXML
    void register(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        root=loader.load();
        RegisterController registerController = loader.getController();
        registerController.personController=personController;
        registerController.init();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}