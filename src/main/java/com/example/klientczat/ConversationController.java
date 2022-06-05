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
import java.util.Arrays;
import java.util.List;

public class ConversationController implements Callback<ListConversation> {

    static ConversationResource resource;
    static final String BASE_URL = "http://localhost:8080/";
    private Retrofit retrofit;
    public void start(){
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        ConversationResource ConversationResource = retrofit.create(ConversationResource.class);

        resource = retrofit.create(ConversationResource.class);
    }

    static List<Conversation> getList(Long id) throws  IOException {
        List<Conversation> result = new ArrayList<>();
        Call<ListConversation> temp = resource.getList(id);
        Response<ListConversation> response = temp.execute();
        if(response.isSuccessful()){
            ListConversation temp2 = response.body();
            result = response.body().getEmbedded().getConversationList();

            return result;
        }


            return null;
        }

    static Conversation addConversation(Conversation conversation) throws IOException {
        Call<Conversation> retrofitCall = resource.addConversation(conversation);
        Response<Conversation> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unknown error");
        }

        return response.body();
    }

    static Conversation deleteConversations(Long id) throws IOException {
        Call<Conversation> retrofitCall = resource.delete(id);
        Response<Conversation> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unknown error");
        }

        return response.body();
    }

    @Override
    public void onResponse(Call<ListConversation> call, Response<ListConversation> response) {
        if(response.isSuccessful()) {
            ListConversation changesList = response.body();

            changesList.getEmbedded().getConversationList().forEach(change -> System.out.println(change.getFirstId()));
        } else {
            System.out.println(response.errorBody());
        }
    }
    @Override
    public void onFailure(Call<ListConversation> call, Throwable t) {
        t.printStackTrace();
    }
}
