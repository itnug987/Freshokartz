package com.freshokartz;

import com.freshokartz.Cart.cart_items;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartApi {

    String DJANGO_SITE = "http://10.0.2.2:8000/";

    @GET("api/v1/cart/current/")
    Call<cart_items> getDetail(@Header("Authorization") String djangoToken);

    @PATCH("api/v1/cart/{cart_id}/")
    Call<CartOrder> addtocart(@Path("cart_id") int cart_id, @Header("Authorization") String djangoToken, @Body CartOrder cartOrder);

    @POST("api/v1/cart/")
    Call<CartOrder> new_addtocart(@Header("Authorization") String djangoToken,@Body CartOrder cartOrder);


}
