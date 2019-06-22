package com.freshokartz;

import com.freshokartz.Area.AreaList;
import com.freshokartz.Category.PostList;
import com.freshokartz.City.CityList;
import com.freshokartz.Product.ProductList;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class CatApi {

    private static final String url = "http://13.127.236.125/api/v1/";

    public static PostService postService = null;

    public static PostService getService(){
        if (postService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService = retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService{
//        @GET("categorytrees/")
//        Call<PostList> getPostList();
//
//        @GET("products/")
//        Call<ProductList> getProductList();
@GET("categorytrees/")
Call<PostList> getPostList();

//        @GET("products/")
//        Call<ProductList> getProductList();


        @GET("/api/v1/products/product_by_category_id/{id}/")
        Call<ProductList> getProductsByCateGory(@Path("id") int id);
        @GET("city/")
        Call<CityList> getCity();

        @GET("area/")
        Call<AreaList> getArea();
    }
}
