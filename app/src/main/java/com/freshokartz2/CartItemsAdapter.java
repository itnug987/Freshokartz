package com.freshokartz2;

import com.freshokartz2.Cart.cart_items;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {

    Context ctx;
    private List<Order_Items> items;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(CartApi.DJANGO_SITE)
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    CartApi cartApi = retrofit.create(CartApi.class);

    public CartItemsAdapter(Context ctx, List<Order_Items> items) {
        this.ctx = ctx;
        this.items = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        final Order_Items c = items.get(position);
        viewHolder.title.setText(c.getProduct_name());
        viewHolder.price.setText(c.getPrice().toString());
        viewHolder.quantity_ordered.setText(c.getQuantity_ordered().toString());

        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String skId = items.get(position).getSku();
                Intent gintent = new Intent(ctx, ProductsPrime.class);
                gintent.putExtra("skId",skId);
                ctx.startActivity(gintent);
            }
        });

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_cart_item(c.getSku(), c.getQuantity_ordered());

            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView price;
        public TextView quantity_ordered;
        public MaterialRippleLayout lyt_parent;

        public Button edit, delete;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            quantity_ordered = (TextView) v.findViewById(R.id.quantity_ordered);
            price = (TextView) v.findViewById(R.id.price);

            lyt_parent = (MaterialRippleLayout) v.findViewById(R.id.lyt_parent);
            edit = v.findViewById(R.id.edit);
            delete = v.findViewById(R.id.delete);

        }

    }

    public void delete_cart_item(final String sku, final Double quantity_ordered ){

       SessionManagement session = new SessionManagement(ctx);
        HashMap<String, String> user = session.getUserDetails();

        String token = user.get(SessionManagement.KEY_TOKEN);

        String TokenRequest = "Token "+ token;
        Call<cart_items> result = cartApi.getDetail(TokenRequest);

        result.enqueue(new Callback<cart_items>() {
            @Override
            public void onResponse(Call<cart_items> call, Response<cart_items> response) {
                cart_items items = response.body();

                int cart_id = items.getResults().get(0).getCart_id();

                delete(cart_id, sku,quantity_ordered);
            }

            @Override
            public void onFailure(Call<cart_items> call, Throwable t) {

            }
        });

    }

    public void delete(int cart_id, String sku, Double quantity_ordered){
        CartItem cartItem = new CartItem(sku,0.01);
        List<CartItem> cart = new ArrayList<CartItem>();
        cart.add(cartItem);

        CartOrder cartOrder = new CartOrder(cart);

        SessionManagement session = new SessionManagement(ctx);
        HashMap<String, String> user = session.getUserDetails();

        String token = user.get(SessionManagement.KEY_TOKEN);

        String TokenRequest = "Token "+ token;



        Call<CartOrder> call = cartApi.addtocart(cart_id, TokenRequest, cartOrder);

        call.enqueue(new Callback<CartOrder>() {
            @Override
            public void onResponse(Call<CartOrder> call, Response<CartOrder> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        Toast.makeText(ctx, "Deleted from cart", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(ctx, Buynow_order.class);
                        ctx.startActivity(i);

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