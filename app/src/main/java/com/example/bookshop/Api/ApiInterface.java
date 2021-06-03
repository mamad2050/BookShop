package com.example.bookshop.Api;

import com.example.bookshop.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("registration/")
    Call<User> register(@Body User user);


}
