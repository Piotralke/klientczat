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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.hateoas.EntityModel;

import java.io.IOException;
import java.util.List;

public class ChatController {
    static ConversationController ConversationController = new ConversationController();
    static{
        ConversationController.start();
    }
    static MessageController messageController = new MessageController();
    static{
        messageController.start();
    }
    public Person user;
    public PersonController personController;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label nameLabel;
    @FXML
    private ListView list;
    @FXML
    private TextField newConvText;
    @FXML
    private Label errorLabel;
    @FXML
    private Button addButton;

    static public boolean refreshConv;
    private int convSize=0;
    void refreshChat() {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (refreshConv) {
                    try {
                        sleep(500);
                        List<Conversation> listConversations = ConversationController.getList(user.getId());
                        if (listConversations != null) {
                            if (listConversations.size() != convSize) {
                                convSize = listConversations.size();
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        list.getItems().clear();
                                        for (Conversation conversation : listConversations) {
                                            if(conversation.getFirstId().equals(user.getId())){
                                                try {
                                                    list.getItems().add(conversation.getId()+" "+ personController.findById(conversation.secondId).getName());
                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }else{
                                                try {
                                                    list.getItems().add(conversation.getId()+" "+ personController.findById(conversation.firstId).getName());
                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
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

    public void init(Person person, PersonController personController) throws IOException {
        nameLabel.setText(person.getName());
        addButton.setDisable(true);
        newConvText.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });
        this.user=person;
        this.personController=personController;
        List<Conversation> conversationList = ConversationController.getList(person.getId());
        if(conversationList!=null){
            for(Conversation conversation : conversationList){
                if(conversation.getFirstId().equals(person.getId())){
                    list.getItems().add(conversation.getId()+" "+ personController.findById(conversation.secondId).getName());
                }else{
                    list.getItems().add(conversation.getId()+" "+ personController.findById(conversation.firstId).getName());
                }
            }
            list.setOnMouseClicked(mouseEvent -> {
                int spacja = list.getSelectionModel().getSelectedItem().toString().indexOf(" ");
                Long idConv = Long.valueOf(list.getSelectionModel().getSelectedItem().toString().substring(0,spacja));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("conversation.fxml"));
                try {
                    root=loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ConvController convController = loader.getController();
                String fullName = list.getSelectionModel().getSelectedItem().toString().substring(spacja+1);
                int spacja2 = fullName.indexOf(" ");
                String name = fullName.substring(0,spacja2);
                String lastName = fullName.substring(spacja2+1);
                try {
                    convController.init(idConv,person,personController,messageController,name,lastName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                refreshConv=false;
                stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            });
            refreshConv=true;
            convSize=conversationList.size();
            refreshChat();
        }
    }

    @FXML
    public void change (Event event) throws IOException{
        refreshConv=false;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change.fxml"));
        root=loader.load();
        ChangeController changeController = loader. getController();
        changeController.init(this.user, this.personController,messageController, ConversationController);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void addConv() throws IOException {
        Person tmp = personController.findByLogin(newConvText.getText());
        if(tmp!=null){
            Conversation conversation = new Conversation(this.user.getId(), (tmp.getId()));
                ConversationController.addConversation(conversation);
            if(convSize==0){
                refreshChat();
            }
        }else{
            errorLabel.setText("Nie znaleziono");
        }
    }

    @FXML
    public void logOut(Event event) throws IOException {
        refreshConv=false;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
