package com.example.klientczat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.springframework.hateoas.EntityModel;

import java.io.IOException;
import java.util.List;

public class ChatController {
    static ConversationController ConversationController = new ConversationController();
    static{
        ConversationController.start();
    }

    public Person user;

    @FXML
    private Label nameLabel;
    @FXML
    private ListView list;
    public void init(Person person) throws IOException {
        nameLabel.setText(person.getName());
        List<Conversation> conversationList = ConversationController.getList(person.getId());
      //  System.out.println(conversationList.size());
      //  for(Conversation conversation : conversationList){
       //     System.out.println(conversation.toString());
      //      list.getItems().add(conversation.getId());
    //    }
    }

    @FXML
    public void logOut(){}
}
