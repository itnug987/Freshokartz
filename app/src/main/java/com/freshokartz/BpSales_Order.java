package com.freshokartz;

import com.freshokartz.BpSalesOrder.SalesOrder;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BpSales_Order {

    String DJANGO_SITE = "http://10.0.2.2:8000/";


    @GET("api/v1/bp/sales_order/past/")
    Call<SalesOrder> getOrderHistory(@Header("Authorization") String djangoToken);

    @POST("api/v1/bp/sales_order/")
    Call<SalesOrderResponseBody> add_bp_sales_order(@Header("Authorization") String djangoToken, @Body SalesOrderResponseBody salesOrderResponseBody);

    @GET("api/v1/bp/sales_order/current/")
    Call<SalesOrder> getCurrentOrders(@Header("Authorization") String djangoToken);
}
