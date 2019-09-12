package com.freshokartz2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.freshokartz2.Product.ProductList;
import com.freshokartz2.Product.Result;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsShow extends AppCompatActivity {
    ImageButton cartbag;
    RecyclerView recyclerView;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_show);

        session = new SessionManagement(getApplicationContext());

        Intent intent = getIntent();
        int id = intent.getIntExtra("refId",121);
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HashMap<String, String> user = session.getUserDetails();

        String token = user.get(SessionManagement.KEY_TOKEN);

        if(session.isLoggedIn()) {
            getProductsCat_Registered(id,token );
        }
        else
            getProductsCat(id);


        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Products");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cartbag = findViewById(R.id.cartbag);
        cartbag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductsShow.this, Buynow_order.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if (item_id == android.R.id.home) {
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private void getProductsCat(int id){
            Call<ProductList> result = CatApi.getService().getProductsByCateGory_Guest(id);
            result.enqueue(new Callback<ProductList>() {
                @Override
                public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                    ProductList productList = response.body();
                    List<Result> main = productList.getResults();
                    Toast.makeText(ProductsShow.this, "Done", Toast.LENGTH_SHORT).show();
                    recyclerView.setAdapter(new ProAdapter(ProductsShow.this,productList.getResults()));
                }

                @Override
                public void onFailure(Call<ProductList> call, Throwable t) {

                }
            });
    }

    private void getProductsCat_Registered(int id, String token){
        Call<ProductList> result = CatApi.getService().getProductsByCateGory_Registered(id, token);
        result.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                ProductList productList = response.body();
                List<Result> main = productList.getResults();
                Toast.makeText(ProductsShow.this, "Done", Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(new ProAdapter(ProductsShow.this,productList.getResults()));
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {

            }
        });
    }


}
