package com.freshokartz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.freshokartz.Product.ProductList;
import com.freshokartz.Product.Result;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsShow extends AppCompatActivity {

    Spinner spinner;
    Button buyNow;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_show);

        Intent intent = getIntent();
        int id = intent.getIntExtra("refId",121);
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getProductsCat(id);

    }

    private void getProductsCat(int id){
        Call<ProductList> result = CatApi.getService().getProductsByCateGory(id);
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
