package com.freshokartz.Product;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderInfo {

    @SerializedName("expected_delivery_date")
    @Expose
    private String expectedDeliveryDate;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("billing_address")
    @Expose
    private Integer billingAddress;
    @SerializedName("shipping_address")
    @Expose
    private Integer shippingAddress;
    @SerializedName("coupon_code")
    @Expose
    private Object couponCode;
    @SerializedName("shipping_method")
    @Expose
    private String shippingMethod;
    @SerializedName("shipping_fee")
    @Expose
    private String shippingFee;
    @SerializedName("order_type")
    @Expose
    private String orderType;
    @SerializedName("order_items")
    @Expose
    private List<OrderItem> orderItems = null;

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Integer billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Integer getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Integer shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Object getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(Object couponCode) {
        this.couponCode = couponCode;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(String shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
