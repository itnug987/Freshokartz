package com.freshokartz2;

import com.freshokartz2.Area.AreaList;
import com.freshokartz2.Category.PostList;
import com.freshokartz2.City.CityList;
import com.freshokartz2.Product.OrderInfo;
import com.freshokartz2.Product.ProductList;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class CatApi {

    private static final String url = "http://13.233.247.56/api/v1/";

    public static PostService postService = null;

    public static PostService getService() {
        if (postService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService = retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService {
        //        @GET("categorytrees/")
//        Call<PostList> getPostList();
//
//        @GET("products/")
//        Call<ProductList> getProductList();
        @GET("categorytrees/")
        Call<PostList> getPostList();

//        @GET("products/")
//        Call<ProductList> getProductList();

        @GET("products/{id}/")
        Call<ProductList> getProductsDescription(@Path("id") String id);


        @GET("/api/v1/products/product_by_category_id/{id}/")
        Call<ProductList> getProductsByCateGory_Guest(@Path("id") int id);

        @GET("/api/v1/products/product_by_category_id/{id}/")
        Call<ProductList> getProductsByCateGory_Registered(@Path("id") int id, @Header("Authorization") String djangoToken);


        @GET("city/")
        Call<CityList> getCity();

        @GET("area/")
        Call<AreaList> getArea();

        @POST("bp/sales_order/")
        Call<OrderInfo> placeOrder(@Header("Authorization") String djangoToken
                                 ,@Body OrderInfo orderInfo);

    }
}
