package com.freshokartz2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartOrder {

    @SerializedName("order_items")
    @Expose
    private List<CartItem> cartItem = null;

    public CartOrder(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }
}
