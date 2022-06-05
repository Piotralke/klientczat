package com.example.klientczat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MessageList {

    @SerializedName("_embedded")
    @Expose
    public MessageArray messageArray;

    public MessageList() {
    }


    public MessageList(MessageArray messageArray) {
        super();
        this.messageArray = messageArray;
    }

    public MessageArray getMessageArray() {
        return messageArray;
    }

    public void setMessageList(MessageArray messageArray) {
        this.messageArray = messageArray;
    }


}
