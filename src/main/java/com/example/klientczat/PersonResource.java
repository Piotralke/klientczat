package com.example.klientczat;

import retrofit2.Call;
import retrofit2.http.*;

public interface PersonResource {

    @GET("/people/{login}")
    Call<Person> login(@Path("login") String login);

    @GET("/people/findById/{id}")
    Call<Person> findById(@Path("id") Long id);

    @POST("/people")
    Call<Person> register(@Body Person newPerson);

    @PUT("/people/{id}")
    Call<Person> change(@Body Person newPerson, @Path("id") Long id);

    @DELETE("/people/{id}")
    Call<Person> deleteById(@Path("id") Long id);

}
