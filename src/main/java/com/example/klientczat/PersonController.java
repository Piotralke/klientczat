package com.example.klientczat;

import java.io.IOException;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class PersonController implements Callback<CollectionModel<EntityModel<Person>>> {
    PersonResource resource;
    static final String BASE_URL = "http://localhost:8080/";
    private Retrofit retrofit;
    public void start(){
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        PersonResource personResource = retrofit.create(PersonResource.class);

        resource = retrofit.create(PersonResource.class);
    }


    EntityModel<Person> login(String login) throws IOException{
        Call<CollectionModel<EntityModel<Person>>> temp = resource.login(login);
        Response<CollectionModel<EntityModel<Person>>> response = temp.execute();
        if(response.isSuccessful()){
            CollectionModel<EntityModel<Person>> temp2 = response.body();
            return  temp2.getContent().stream().findFirst().get();
        }
        return null;
    }
    @Override
    public void onResponse(Call<CollectionModel<EntityModel<Person>>> call, Response<CollectionModel<EntityModel<Person>>> response) {
        if(response.isSuccessful()) {
            CollectionModel<EntityModel<Person>> changesList = response.body();
            changesList.forEach(change -> System.out.println(change.getContent().getName()));
        } else {
            System.out.println(response.errorBody());
        }
    }
    @Override
    public void onFailure(Call<CollectionModel<EntityModel<Person>>> call, Throwable t) {
        t.printStackTrace();
    }
}
