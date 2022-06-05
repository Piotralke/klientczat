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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Embedded.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("messageList");
        sb.append('=');
        sb.append(((this.messageList == null)?"<null>":this.messageList));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.messageList == null)? 0 :this.messageList.hashCode()));
        return result;
    }



}
