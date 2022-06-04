package com.example.klientczat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MessageResource {

    @GET("/people/{id}/conversations/{convId}/messages")
    Call<ListResponse> getList(@Path("id") Long id);
}
