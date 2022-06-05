package com.example.klientczat;

import java.io.IOException;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PersonController implements Callback<Person> {
    static PersonResource resource;
    static final String BASE_URL = "http://localhost:8080/";
    private Retrofit retrofit;
    public void start(){
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        PersonResource personResource = retrofit.create(PersonResource.class);

        resource = retrofit.create(PersonResource.class);
    }


    static Person login(String login, String password) throws IOException{
        Call<Person> temp = resource.login(login);
        Response<Person> response = temp.execute();
        if(response.isSuccessful()){
            Person temp2 = response.body();
            if(temp2.getPassword().equals(password))
            {
                return  temp2;
            }
        }
        return null;
    }

    static Person findByLogin(String login) throws IOException{
        Call<Person> temp = resource.login(login);
        Response<Person> response = temp.execute();
        if(response.isSuccessful()){
            Person temp2 = response.body();
                return  temp2;
        }
        return null;
    }

    static Person findById(Long id) throws IOException{
        Call<Person> temp = resource.findById(id);
        Response<Person> response = temp.execute();
        if(response.isSuccessful()){
            System.out.println(response.body().toString());
            Person temp2 = response.body();
                return  temp2;
        }
        return null;
    }

    static Person register(Person person) throws IOException {
        Call<Person> retrofitCall = resource.register(person);
        Response<Person> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unknown error");
        }

        return response.body();
    }

    static Person change(Person person) throws IOException{
        Call<Person> retrofitCall = resource.change(person,person.getId());
        Response<Person> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unknown error");
        }

        return response.body();
    }

    static Person delete(Long id) throws IOException {
        Call<Person> retrofitCall = resource.deleteById(id);
        Response<Person> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unknown error");
        }

        return response.body();
    }

    @Override
    public void onResponse(Call<Person> call, Response<Person> response) {
        if(response.isSuccessful()) {
            Person changesList = response.body();
            System.out.println(changesList.getName());
            //changesList.forEach(change -> System.out.println(change.getContent().getName()));
        } else {
            System.out.println(response.errorBody());
        }
    }
    @Override
    public void onFailure(Call<Person> call, Throwable t) {
        t.printStackTrace();
    }
}
