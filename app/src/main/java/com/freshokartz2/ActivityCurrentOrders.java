package com.freshokartz2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.freshokartz2.BpSalesOrder.Result;
import com.freshokartz2.BpSalesOrder.SalesOrder;
import com.freshokartz2.adapter.AdapterOrderHistory;
import com.freshokartz2.utils.Tools;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityCurrentOrders extends AppCompatActivity {
    private RecyclerView recyclerView;
    private View parent_view;


    SessionManagement session;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BpSales_Order.DJANGO_SITE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    BpSales_Order currentOrdersApi = retrofit.create(BpSales_Order.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_orders);


        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();

        String token =  user.get(SessionManagement.KEY_TOKEN);

        initToolbar();
        iniComponent();

        getCurrentOrdersDetails(token);
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
        actionBar.setTitle("Current Orders");
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

    private void getCurrentOrdersDetails(String token){
        String Token_request = "Token " + token;

        Call<SalesOrder> result = currentOrdersApi.getCurrentOrders(Token_request);
        result.enqueue(new Callback<SalesOrder>() {
            @Override
            public void onResponse(Call<SalesOrder> call, Response<SalesOrder> response) {
                SalesOrder salesOrder = response.body();
                //  Toast.makeText(ActivityOrderHistory.this, salesOrder.getCount(), Toast.LENGTH_SHORT).show();

                List<Result> main = salesOrder.getResults();
                recyclerView.setAdapter(new AdapterOrderHistory(ActivityCurrentOrders.this, salesOrder.getResults()));
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
