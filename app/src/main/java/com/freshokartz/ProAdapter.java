package com.freshokartz;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.freshokartz.Product.Result;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.List;

public class ProAdapter extends RecyclerView.Adapter<ProAdapter.ProAdapterViewHolder> {

    String[] data;
    private Context context;
    private List<Result> items;

    public ProAdapter(Context context, List<Result> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ProAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout, viewGroup, false);
        return new ProAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProAdapterViewHolder proAdapterViewHolder, final int i) {
        Result result = items.get(i);
        proAdapterViewHolder.textView.setText(result.getProductName());
        proAdapterViewHolder.price.setText(String.valueOf("Rs. " + result.getPrice()));
        String url = "http://13.127.236.125/" + (String) result.getProductImage();
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().override(140, 140))
                .into(proAdapterViewHolder.imageView);
        proAdapterViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ProAdapterViewHolder extends RecyclerView.ViewHolder {
        ExpandableRelativeLayout relativeLayout;

        TextView textView, price;
        int finalValue;
        ImageView imageView, plus, minus, dropd;
        EditText incre;
        Button buyNow;

        Spinner spinner;

        public ProAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.out);
            buyNow = itemView.findViewById(R.id.buynow);
            dropd = itemView.findViewById(R.id.dropd);
            buyNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!relativeLayout.isExpanded()) {
                        dropd.setImageResource(R.drawable.upmain);
                        relativeLayout.expand();
                    } else if (relativeLayout.isExpanded()) {
                        dropd.setImageResource(R.drawable.maindown);
                        relativeLayout.collapse();
                    }
                }
            });
            textView = itemView.findViewById(R.id.text);
            incre = itemView.findViewById(R.id.incre);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.img_product);
            spinner = itemView.findViewById(R.id.spin);
            String[] items = new String[]{"Kg", "g"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
            spinner.setAdapter(adapter);

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String value = incre.getText().toString();
                    finalValue = Integer.parseInt(value);
                    finalValue++;
                    incre.setText(String.valueOf(finalValue));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String value = incre.getText().toString();
                    finalValue = Integer.parseInt(value);
                    finalValue--;
                    if (finalValue > 0)
                        incre.setText(String.valueOf(finalValue));
                }
            });
        }
    }


}
