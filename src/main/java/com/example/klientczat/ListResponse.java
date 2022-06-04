package com.example.klientczat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListResponse {

    @SerializedName("conversation")
    @Expose
    public List<Conversation> conversationList = new ArrayList<>();

    public List<Conversation> getConversationList() {
        return conversationList;
    }
}
