package com.freshokartz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartItem {

    @SerializedName("sku")
    @Expose
    private String sku;

    @SerializedName("quantity_ordered")
    @Expose
    private Double quantity_ordered;

    public CartItem(String sku, Double quantity_ordered) {
        this.sku = sku;
        this.quantity_ordered = quantity_ordered;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getQuantity_ordered() {
        return quantity_ordered;
    }

    public void setQuantity_ordered(Double quantity_ordered) {
        this.quantity_ordered = quantity_ordered;
    }
}
