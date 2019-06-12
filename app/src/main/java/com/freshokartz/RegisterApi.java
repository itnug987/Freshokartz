package com.freshokartz;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RegisterApi {

    String DJANGO_SITE = "http://10.0.2.2:8000/";

    @POST("api/v1/businesspartners/")
    Call<RegisterDetail> register(@Body RegisterDetail registerDetail);


}
