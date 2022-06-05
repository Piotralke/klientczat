
package com.example.klientczat;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Embedded {

    @SerializedName("conversationList")
    @Expose
    public List<Conversation> conversationList = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Embedded() {
    }

    /**
     *
     * @param conversationList
     */
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Embedded.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("conversationList");
        sb.append('=');
        sb.append(((this.conversationList == null)?"<null>":this.conversationList));
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
        result = ((result* 31)+((this.conversationList == null)? 0 :this.conversationList.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Embedded) == false) {
            return false;
        }
        Embedded rhs = ((Embedded) other);
        return ((this.conversationList == rhs.conversationList)||((this.conversationList!= null)&&this.conversationList.equals(rhs.conversationList)));
    }

}
