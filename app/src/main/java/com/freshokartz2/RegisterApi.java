package com.freshokartz2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterApi {

    String DJANGO_SITE = "http://13.233.247.56/";

    @POST("api/v1/businesspartners/")
    Call<RegisterDetail> register(@Body RegisterDetail registerDetail);


}
