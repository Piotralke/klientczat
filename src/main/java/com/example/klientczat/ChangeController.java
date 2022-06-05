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
import java.util.List;

public class ChangeController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public PersonController personController;
    public MessageController messageController;
    public ConversationController conversationController;
    public Person person;
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

    public void init(Person person, PersonController personController, MessageController messageController, ConversationController conversationController){
        this.person=person;
        this.personController=personController;
        this.messageController=messageController;
        this.conversationController=conversationController;
        loginText.setText(person.getLogin());
        passwordText.setText(person.getPassword());
        nameText.setText(person.getFirstName());
        surnameText.setText(person.getLastName());
    }

    @FXML
    public void putChange(Event event) throws IOException  {
        if(personController.findByLogin(loginText.getText())==null){
            errorLabel.setText("");
            Person person2 = new Person(nameText.getText(),surnameText.getText(),loginText.getText(),passwordText.getText());
            person2.setId(person.getId());
            this.person=person2;
            personController.change(person);
            goBack(event);
        }else{
            errorLabel.setText("Uzytkownik o takim loginie juz istnieje!");
        }
    }

    @FXML
    public void deleteUser(Event event) throws IOException {
        List<Conversation> temp = conversationController.getList(this.person.getId());
        temp.forEach(test->{
            try {
                messageController.delete(test.getId());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        temp=null;
        conversationController.deleteConversations(this.person.getId());
        personController.delete(this.person.getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goBack(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
        root=loader.load();
        ChatController chatController = loader.getController();
        chatController.init(this.person,this.personController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
