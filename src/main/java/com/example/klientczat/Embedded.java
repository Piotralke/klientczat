
package com.example.klientczat;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Embedded {

    @SerializedName("conversationList")
    @Expose
    public List<Conversation> conversationList = null;

    public Embedded() {
    }

    public Embedded(List<Conversation> conversationList) {
        super();
        this.conversationList = conversationList;
    }

    public List<Conversation> getConversationList() {
        return conversationList;
    }

    public void setConversationList(List<Conversation> conversationList) {
        this.conversationList = conversationList;
    }


}
