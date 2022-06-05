package com.example.klientczat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Message {
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("conversationId")
    @Expose
    public Long conversationId;
    @SerializedName("senderId")
    @Expose
    public Long senderId;
    public Message() {}

    Message(Long conversationId,Long senderId, String text) {
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.text = text;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public Long getsenderId() {
        return senderId;
    }

    public void setsenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Message))
            return false;
        Message message = (Message) o;
        return Objects.equals(this.id, message.id) && Objects.equals(this.conversationId,message.conversationId)
                && Objects.equals(this.text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.conversationId, this.text);
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + this.id + ", conversationId=" + this.conversationId + ", senderID=" + this.senderId +  ", text='" + this.text +  '}';
    }
}
