
package com.example.klientczat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListConversation {

    @SerializedName("_embedded")
    @Expose
    public Embedded embedded;


    public ListConversation() {
    }

    public ListConversation(Embedded embedded) {
        super();
        this.embedded = embedded;
    }

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ListConversation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("embedded");
        sb.append('=');
        sb.append(((this.embedded == null)?"<null>":this.embedded));
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
        result = ((result* 31)+((this.embedded == null)? 0 :this.embedded.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ListConversation) == false) {
            return false;
        }
        ListConversation rhs = ((ListConversation) other);
        return (((this.embedded == rhs.embedded)||((this.embedded!= null)&&this.embedded.equals(rhs.embedded))));
    }

}
