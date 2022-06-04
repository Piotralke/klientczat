package com.example.klientczat;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PersonResource {
    @GET("/people/{id}")
    Call<Person> login(@Path("id") String login);

    @POST("/people")
    Call<ResponseEntity<?>> register(@RequestBody Person newPerson);
}
