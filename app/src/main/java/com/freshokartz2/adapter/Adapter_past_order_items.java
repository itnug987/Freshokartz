package com.freshokartz2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freshokartz2.Order_Items;
import com.freshokartz2.R;

import java.util.List;

public class Adapter_past_order_items extends RecyclerView.Adapter<Adapter_past_order_items.ViewHolder> {


    private Context ctx;
    private List<Order_Items> items ;

    public Adapter_past_order_items(Context ctx, List<Order_Items> items) {
        this.ctx = ctx;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(R.layout.past_order_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        final Order_Items c = items.get(position);
        viewHolder.title.setText(c.getProduct_name());
        viewHolder.price.setText(c.getPrice().toString());
        viewHolder.quantity_ordered.setText(c.getQuantity_ordered().toString());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView quantity_ordered;
        public TextView price;

        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            quantity_ordered = v.findViewById(R.id.quantity_ordered);
            price =  v.findViewById(R.id.price);

        }



    }
}
