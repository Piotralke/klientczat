package com.example.klientczat;

import retrofit2.Call;
import retrofit2.http.*;

public interface MessageResource {

    @GET("/conversations/{convId}/messages")
    Call<MessageList> getList(@Path("convId") Long id);

    @POST("/conversations/{convId}/messages")
    Call<Message> sendMessage(@Body Message newMessage, @Path("convId") Long id);

    @DELETE("/conversations/{convId}/messages")
    Call<Message> deleteMessages(@Path("convId") Long id);
}
