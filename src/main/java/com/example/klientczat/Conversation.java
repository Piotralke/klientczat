package com.example.klientczat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Conversation {
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("firstId")
    @Expose
    public Long firstId;
    @SerializedName("secondId")
    @Expose
    public Long secondId;

    public Conversation() {}

    public Conversation(Long firstId,Long secondId) {

        this.firstId = firstId;
        this.secondId = secondId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFirstId() {
        return firstId;
    }

    public void setFirstId(Long firstId) {
        this.firstId = firstId;
    }

    public Long getSecondId() {
        return secondId;
    }

    public void setSecondId(Long secondId) {
        this.secondId = secondId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Conversation))
            return false;
        Conversation conversation = (Conversation) o;
        return Objects.equals(this.id, conversation.id) && Objects.equals(this.firstId, conversation.firstId)
                && Objects.equals(this.secondId, conversation.secondId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstId, this.secondId);
    }

    @Override
    public String toString() {
        return "Conversation{" + "id=" + this.id + ", firstId='" + this.firstId + '\'' + ", secondId=" + this.secondId + '}';
    }
}