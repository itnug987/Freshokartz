package com.freshokartz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.freshokartz.Cart.Result;
import com.freshokartz.Cart.cart_items;
import com.freshokartz.Product.ProductList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Buynow_order extends AppCompatActivity {
    TextView expectedDate;
RecyclerView recyclerView;
    Button place_order;
    TextView amount, total_amount;
    AlertDialog.Builder alertBuilder;
    SessionManagement session;


    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(CartApi.DJANGO_SITE)
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    CartApi cartApi = retrofit.create(CartApi.class);
    SessionManagement sessionManagement;

    BpSales_Order sales_orderApi = retrofit.create(BpSales_Order.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buynow_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sessionManagement = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = sessionManagement.getUserDetails();

        final String token = user.get(SessionManagement.KEY_TOKEN);

        getProductsCat(token);

        place_order = findViewById(R.id.place_order);

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_bp_sales_order(token);
            }
        });

        amount = findViewById(R.id.total_amount);
        total_amount = findViewById(R.id.total_amount_payable);

        expectedDate = findViewById(R.id.expected);
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        //String todayAsString = dateFormat.format(today);

        String tomorrowAsString = dateFormat.format(tomorrow);
        expectedDate.setText("Expected delivery date : "+tomorrowAsString);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if (item_id == android.R.id.home) {
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private void getProductsCat(String token){
        String TokenRequest = "Token "+ token;
        Call<cart_items> result = cartApi.getDetail(TokenRequest);
        result.enqueue(new Callback<cart_items>() {
            @Override
            public void onResponse(Call<cart_items> call, Response<cart_items> response) {
                cart_items items = response.body();
                if(items.getCount()>0) {
                    List<Result> main = items.getResults();
                    amount.setText(String.valueOf(items.getResults().get(0).getOrder_amount()));
                    total_amount.setText(String.valueOf(items.getResults().get(0).getOrder_amount()));
                    recyclerView.setAdapter(new CartItemsAdapter(Buynow_order.this, items.getResults().get(0).getOrder_items()));
                }
                }

            @Override
            public void onFailure(Call<cart_items> call, Throwable t) {

            }
        });
    }

    private void add_bp_sales_order(String token){
        String TokenRequest = "Token "+ token;

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.ENGLISH);
        String tomorrowAsString = dateFormat.format(tomorrow);

        List<CartItem> cartItems= new ArrayList<>();

        SalesOrderResponseBody salesOrderResponseBody = new SalesOrderResponseBody(tomorrowAsString, "cash_on_delivery",1,1,null,"free_shipping",0.00, "android_app", cartItems);
        Call<SalesOrderResponseBody> call = sales_orderApi.add_bp_sales_order(TokenRequest,salesOrderResponseBody);

        call.enqueue(new Callback<SalesOrderResponseBody>() {
            @Override
            public void onResponse(Call<SalesOrderResponseBody> call, Response<SalesOrderResponseBody> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        Toast.makeText(Buynow_order.this, "Order is placed", Toast.LENGTH_SHORT).show();

                        alertBuilder = new AlertDialog.Builder(Buynow_order.this);

                        alertBuilder.setMessage(" Your order is place. Keep Shopping. ")
                                    .setCancelable(false)
                                    .setPositiveButton("Return to Shopping", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            create_new_cart();

                                            Intent i = new Intent(Buynow_order.this, ActivityMain.class);
                                            startActivity(i);
                                        }
                                    });

                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.setTitle("Order placed");
                        alertDialog.show();
                        //Intent i = new Intent(Buynow_order.this, ActivityMain.class);
                        //startActivity(i);

                    }
                } else {
                    Log.d("fail", "fail");
                }
            }

            @Override
            public void onFailure(Call<SalesOrderResponseBody> call, Throwable t) {

            }
        });
    }

    private void create_new_cart(){
        List<CartItem> cart = new ArrayList<CartItem>();
        CartOrder cartOrder = new CartOrder(cart);

        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();

        String token = user.get(SessionManagement.KEY_TOKEN);

        String TokenRequest = "Token "+ token;
        Call<CartOrder> call = cartApi.new_addtocart(TokenRequest, cartOrder);

        call.enqueue(new Callback<CartOrder>() {
            @Override
            public void onResponse(Call<CartOrder> call, Response<CartOrder> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        Toast.makeText(Buynow_order.this, "New cart is created", Toast.LENGTH_SHORT).show();


                    }
                } else {
                    Log.d("fail", "fail");
                }
            }

            @Override
            public void onFailure(Call<CartOrder> call, Throwable t) {

            }
        });
    }



}
