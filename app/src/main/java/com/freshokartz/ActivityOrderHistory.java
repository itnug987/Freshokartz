package com.freshokartz;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.freshokartz.BpSalesOrder.Result;
import com.freshokartz.BpSalesOrder.SalesOrder;
import com.freshokartz.adapter.AdapterOrderHistory;
import com.freshokartz.adapter.AdapterShoppingCart;
import com.freshokartz.data.DatabaseHandler;
import com.freshokartz.data.SharedPref;
import com.freshokartz.model.Info;
import com.freshokartz.model.Order;
import com.freshokartz.utils.Tools;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityOrderHistory extends AppCompatActivity {

    private View parent_view;
    private RecyclerView recyclerView;

    private AdapterOrderHistory adapter;

    SessionManagement session;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BpSales_Order.DJANGO_SITE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    BpSales_Order sales_orderApi = retrofit.create(BpSales_Order.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);



        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();

        String token =  user.get(SessionManagement.KEY_TOKEN);

        initToolbar();
        iniComponent();

        getOrderHistoryDetails(token);

    }

    private void iniComponent() {
        parent_view = findViewById(android.R.id.content);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initToolbar() {
        ActionBar actionBar;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.title_activity_history);
        Tools.systemBarLolipop(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if (item_id == android.R.id.home) {
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getOrderHistoryDetails(String token){
        String Token_request = "Token " + token;

        Call<SalesOrder> result = sales_orderApi.getOrderHistory(Token_request);
        result.enqueue(new Callback<SalesOrder>() {
            @Override
            public void onResponse(Call<SalesOrder> call, Response<SalesOrder> response) {
                SalesOrder salesOrder = response.body();
                //  Toast.makeText(ActivityOrderHistory.this, salesOrder.getCount(), Toast.LENGTH_SHORT).show();

                List<Result> main = salesOrder.getResults();
                recyclerView.setAdapter(new AdapterOrderHistory(ActivityOrderHistory.this, salesOrder.getResults()));
                View lyt_no_item = (View) findViewById(R.id.lyt_no_item);

                if (main.size() == 0) {
                    lyt_no_item.setVisibility(View.VISIBLE);
                } else {
                    lyt_no_item.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<SalesOrder> call, Throwable t) {

            }
        });

    }





}
