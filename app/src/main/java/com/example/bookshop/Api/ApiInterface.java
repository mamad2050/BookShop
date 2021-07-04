package com.example.bookshop.Api;

import com.example.bookshop.Model.Message;
import com.example.bookshop.Model.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Users> loginCall(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<Users> registerCall(@Field("username") String username, @Field("email") String email,
                             @Field("phone") String phone, @Field("password") String password);


    @FormUrlEncoded
    @POST("sendComment.php")
    Call<Message> sendCommentCall(@Field("id_book") String id_book,
                                  @Field("username_user") String username_user,
                                  @Field("description") String description,
                                  @Field("rating") String rating,
                                  @Field("positive") String positive,
                                  @Field("negative") String negative,
                                  @Field("title") String title,
                                  @Field("date") String date);

}
