package com.example.klientczat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ConversationResource {

    @GET("/people/{id}/conversations")
    Call<ListConversation> getList(@Path("id") Long id);

    @POST("/conversations")
    Call<Conversation> addConversation(@Body Conversation newConversation);

    @DELETE("/people/{id}/conversations")
    Call<Conversation> delete(@Path("id") Long id);
}
