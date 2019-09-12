package com.freshokartz2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freshokartz2.BpSalesOrder.Result;
import com.freshokartz2.R;
import com.balysv.materialripple.MaterialRippleLayout;

import java.util.List;


public class AdapterOrderHistory extends RecyclerView.Adapter<AdapterOrderHistory.ViewHolder> {

    Context ctx;
    private List<Result> items;

    public AdapterOrderHistory(Context ctx, List<Result> items) {
        this.ctx = ctx;
        this.items = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(R.layout.item_order_history, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        final Result c = items.get(position);
        viewHolder.code.setText(c.getBp_sales_order_id());
        viewHolder.price.setText(c.getOrder_amount().toString());
        viewHolder.status.setText(c.getOrder_status());
        viewHolder.date.setText(c.getExpected_delivery_date());

        viewHolder.past_order_items.setAdapter(new Adapter_past_order_items(ctx, c.getOrder_items()));


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView code;
        public TextView date;
        public TextView price;
        public TextView status;
        public MaterialRippleLayout lyt_parent;

        RecyclerView past_order_items;

        public ViewHolder(View v) {
            super(v);
            code = (TextView) v.findViewById(R.id.code);
            date = (TextView) v.findViewById(R.id.date);
            price = (TextView) v.findViewById(R.id.price);
            status = (TextView) v.findViewById(R.id.status);
            lyt_parent = (MaterialRippleLayout) v.findViewById(R.id.lyt_parent);
            past_order_items = v.findViewById(R.id.past_order_items);

            past_order_items.setLayoutManager(new LinearLayoutManager(ctx));
        }



}

}