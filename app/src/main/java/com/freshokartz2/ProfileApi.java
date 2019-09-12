package com.freshokartz2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ProfileApi {

    String DJANGO_SITE = "http://13.233.247.56/";

    @GET("api/v1/businesspartners/")
    Call<BpResponseBody> getDetail(@Header("Authorization") String djangoToken);


}
