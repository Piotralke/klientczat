package com.example.klientczat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface ConversationResource {

    @GET("/people/{id}/conversations")
    Call<ListResponse> getList(@Path("id") Long id);

}
