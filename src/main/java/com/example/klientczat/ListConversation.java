
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


}
