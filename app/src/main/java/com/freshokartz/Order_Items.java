package com.freshokartz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order_Items {

    @SerializedName("sku")
    @Expose
    private String sku;

    @SerializedName("quantity_ordered")
    @Expose
    private Double quantity_ordered;

    @SerializedName("discount_percent")
    @Expose
    private Double discount_percent;

    @SerializedName("price")
    @Expose
    private Double price;

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

    public Double getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(Double discount_percent) {
        this.discount_percent = discount_percent;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
