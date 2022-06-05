package com.example.klientczat;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class ConvController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private PersonController personController;
    private MessageController messageController;
    private Person person;
    private Long convId;
    private String firstName;
    @FXML
    private Label nameLabel;
    @FXML
    private ListView list;
    @FXML
    private TextField messageText;
    @FXML
    private Button addButton;
    static public boolean refresh;
    private int messageNumber=0;

    void refreshChat() {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (refresh) {
                    try {
                        sleep(500);
                        List<Message> listMessages = messageController.getList(convId);
                        if (listMessages != null) {
                            if (listMessages.size() != messageNumber) {
                                messageNumber = listMessages.size();
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        list.getItems().clear();
                                        for (Message message : listMessages) {
                                            if (!message.getsenderId().equals(person.getId())) {
                                                list.getItems().add(firstName + ": " + message.text);
                                            } else {
                                                list.getItems().add(person.getFirstName() + ": " + message.text);
                                            }
                                        }
                                    }
                                });

                            }
                        }
                        }catch(IOException e){
                            throw new RuntimeException(e);
                        }catch (NullPointerException exception){

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        };
        t1.start();
    }


    public void init(Long id, Person person,PersonController personController,MessageController messageController,String firstName, String lastName) throws  IOException{
        addButton.setDisable(true);
        messageText.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });
        this.person=person;
        this.personController=personController;
        this.messageController=messageController;
        this.convId=id;
        this.firstName=firstName;
        nameLabel.setText(firstName + " " + lastName);
        List<Message> listMessages = messageController.getList(id);
        if(listMessages!=null){
            for(Message message : listMessages){
                if(!message.getsenderId().equals(this.person.getId())){
                    list.getItems().add(firstName + ": " + message.text);
                }else{
                    list.getItems().add(this.person.getFirstName() + ": " + message.text);
                }
            }
            messageNumber= listMessages.size();
            refresh=true;
            refreshChat();
        }

    }

    @FXML
    public void goBack(Event event) throws IOException {
        refresh=false;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
        root=loader.load();
        ChatController chatController = loader.getController();
        chatController.init(person,personController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void sendMessage() throws IOException{
        Message message = new Message(this.convId,this.person.getId(),messageText.getText());
        messageController.addMessage(message);
        if(messageNumber==0){
            refresh=true;
            refreshChat();
        }
    }

    @FXML
    public void save() throws IOException {
        PrintWriter printWriter = new PrintWriter("conversation"+convId+".txt");
        List<Message> listMessages = messageController.getList(convId);
        if(listMessages!=null){
            for(Message message : listMessages){
                if(!message.getsenderId().equals(this.person.getId())){
                    printWriter.println(firstName + ": " + message.text);
                }else{
                    printWriter.println(this.person.getFirstName() + ": " + message.text);
                }
            }
            printWriter.close();
        }
    }
}
