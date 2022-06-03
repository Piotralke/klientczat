package com.example.klientczat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {

    static PersonController personController = new PersonController();
    static{
        personController.start();
    }

    @FXML
    private TextField loginText;
    @FXML
    private PasswordField passwordText;

    @FXML
    void login(ActionEvent event) throws IOException{
        ChatController.user = PersonController.login(loginText.getText());
        if(ChatController.user!=null){
            //zaloguj, zmien scene
            loginText.setText("GIT");
        }else{
            loginText.setText("NIEGIT");
        }
    }

}