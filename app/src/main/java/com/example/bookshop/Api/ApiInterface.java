package com.example.bookshop.Api;

import com.example.bookshop.Model.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("registration")

    Call<User> register(@Query("username") String username,

                        @Query("password1") String password1,
                        @Query("password2") String password2);


}
