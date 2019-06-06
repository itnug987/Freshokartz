package com.freshokartz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginApi {

    String DJANGO_SITE = "http://13.127.236.125/api/v1/businesspartners/";

//    @FormUrlEncoded
//    @POST("bp-login/")
//    Call<User> login(
//            @Field("email") String email,
//            @Field("password") String password
//    );

    // Api-Endpoint inside @POST() method

    @POST("bp-login/")
    Call<User> login(@Body Login login);

    // Gettting the Authorization token
    @GET("auth/")
    Call<List<Posts>> getPost(@Header("Authorization") String djangoToken);
}

