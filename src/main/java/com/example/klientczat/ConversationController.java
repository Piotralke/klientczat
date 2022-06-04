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

public class ConversationController implements Callback<ListResponse> {

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
        Call<ListResponse> temp = resource.getList(id);

        Response<ListResponse> response = temp.execute();
        if(response.isSuccessful()){
            System.out.println(response.body());
            for(Conversation conversation : response.body().getConversationList()){
                result.add(conversation);
            }
           // List<Conversation> result = response.body().getConversationList();

            return result;
        }
        return null;
    }

    @Override
    public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
        if(response.isSuccessful()) {
            ListResponse changesList = response.body();
            //System.out.println(changesList.getName());
            changesList.getConversationList().forEach(change -> System.out.println(change.getFirstId()));
        } else {
            System.out.println(response.errorBody());
        }
    }
    @Override
    public void onFailure(Call<ListResponse> call, Throwable t) {
        t.printStackTrace();
    }
}
