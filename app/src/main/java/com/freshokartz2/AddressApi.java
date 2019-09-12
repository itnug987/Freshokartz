package com.freshokartz2;

import com.freshokartz2.Country.CountryList;
import com.freshokartz2.State.StateList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AddressApi {


    String DJANGO_SITE = "http://13.233.247.56/";

    @POST("api/v1/bp/addresses/")
    Call<AddressDetail> add_address(@Header("Authorization") String DjangoToken, @Body AddressDetail addressDetail);

    @GET("api/v1/state/")
    Call<StateList> getState();

    @GET("api/v1/country/")
    Call<CountryList> getCountry();
}
