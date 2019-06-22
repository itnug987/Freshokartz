package com.freshokartz.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freshokartz.Category.Result;
import com.freshokartz.ProductsShow;
import com.freshokartz.R;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatAdapterViewHolder>{

    String[] data;
    private Context context;
    private List<Result> items;

    public CatAdapter(Context context, List<Result> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CatAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.category_items,viewGroup,false);
        return new CatAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatAdapterViewHolder catAdapterViewHolder, final int i) {
        Result result = items.get(i);
        catAdapterViewHolder.textView.setText(result.getName());
        catAdapterViewHolder.textView.setText(result.getName());
        catAdapterViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = items.get(i).getCategoryTreeId();
                Log.i("yourta",String.valueOf(id));
                Intent intent = new Intent(context, ProductsShow.class);
                intent.putExtra("refId",id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CatAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public CatAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.catName);
        }
    }
}
