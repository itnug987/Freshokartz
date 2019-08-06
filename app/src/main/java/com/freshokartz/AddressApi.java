package com.freshokartz;

import com.freshokartz.City.CityList;
import com.freshokartz.Country.CountryList;
import com.freshokartz.State.StateList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AddressApi {


    String DJANGO_SITE = "http://10.0.2.2:8000/";

    @POST("api/v1/bp/addresses/")
    Call<AddressDetail> add_address(@Header("Authorization") String DjangoToken, @Body AddressDetail addressDetail);

    @GET("api/v1/state/")
    Call<StateList> getState();

    @GET("api/v1/country/")
    Call<CountryList> getCountry();
}
