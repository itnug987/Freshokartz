package com.freshokartz;

import com.freshokartz.Category.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SalesOrderResponseBody {

    @SerializedName("expected_delivery_date")
    @Expose
    private String expected_delivery_date;

    @SerializedName("payment_method")
    @Expose
    private String payment_method;

    @SerializedName("billing_address")
    @Expose
    private Integer billing_address;

    @SerializedName("shipping_address")
    @Expose
    private Integer shipping_address;

    @SerializedName("coupon_code")
    @Expose
    private String coupon_code;

    @SerializedName("shipping_method")
    @Expose
    private String shipping_method;

    @SerializedName("shipping_fee")
    @Expose
    private Double shipping_fee;

    @SerializedName("order_type")
    @Expose
    private String order_type;

    @SerializedName("order_items")
    @Expose
    private List<CartItem> cartItems = null;

    public SalesOrderResponseBody(String expected_delivery_date, String payment_method, Integer billing_address, Integer shipping_address, String coupon_code, String shipping_method, Double shipping_fee, String order_type, List<CartItem> cartItems) {
        this.expected_delivery_date = expected_delivery_date;
        this.payment_method = payment_method;
        this.billing_address = billing_address;
        this.shipping_address = shipping_address;
        this.coupon_code = coupon_code;
        this.shipping_method = shipping_method;
        this.shipping_fee = shipping_fee;
        this.order_type = order_type;
        this.cartItems = cartItems;
    }

    public String getExpected_delivery_date() {
            return expected_delivery_date;
    }

    public void setExpected_delivery_date(String expected_delivery_date) {
        this.expected_delivery_date = expected_delivery_date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public Integer getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(Integer billing_address) {
        this.billing_address = billing_address;
    }

    public Integer getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(Integer shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getShipping_method() {
        return shipping_method;
    }

    public void setShipping_method(String shipping_method) {
        this.shipping_method = shipping_method;
    }

    public Double getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(Double shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
