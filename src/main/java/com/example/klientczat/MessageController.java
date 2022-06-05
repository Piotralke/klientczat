package com.example.klientczat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageController implements Callback<MessageList> {

    static MessageResource resource;
    static final String BASE_URL = "http://localhost:8080/";
    private Retrofit retrofit;
    public void start(){
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        MessageResource MessageResource = retrofit.create(MessageResource.class);

        resource = retrofit.create(MessageResource.class);
    }

    static List<Message> getList(Long id) throws IOException {
        List<Message> result = new ArrayList<>();
        Call<MessageList> temp = resource.getList(id);
        Response<MessageList> response = temp.execute();
        if(response.isSuccessful()){
            MessageList temp2 = response.body();
            result = response.body().getMessageArray().getMessageArray();
            return result;
        }


        return null;
    }

    static Message addMessage(Message message) throws IOException {
        Call<Message> retrofitCall = resource.sendMessage(message, message.getConversationId());
        Response<Message> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unknown error");
        }

        return response.body();
    }

    static Message delete(Long id) throws IOException {
        Call<Message> retrofitCall = resource.deleteMessages(id);
        Response<Message> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unknown error");
        }

        return response.body();
    }

    @Override
    public void onResponse(Call<MessageList> call, Response<MessageList> response) {
        if(response.isSuccessful()) {
            MessageList changesList = response.body();

        } else {
            System.out.println(response.errorBody());
        }
    }
    @Override
    public void onFailure(Call<MessageList>call, Throwable t) {
        t.printStackTrace();
    }
}
