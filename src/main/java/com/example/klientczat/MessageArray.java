package com.example.klientczat;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageArray {

    @SerializedName("messageList")
    @Expose
    public List<Message> messageList = null;


    public MessageArray() {
    }

    public MessageArray(List<Message> messageList) {
        super();
        this.messageList = messageList;
    }

    public List<Message> getMessageArray() {
        return messageList;
    }

    public void setMessageArray(List<Message> messageList) {
        this.messageList = messageList;
    }




}
