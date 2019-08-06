package com.freshokartz.BpSalesOrder;

import com.freshokartz.Order_Items;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("bp_sales_order_id")
    @Expose
    private String bp_sales_order_id;

    @SerializedName("expected_delivery_date")
    @Expose
    private String expected_delivery_date;

    @SerializedName("billing_name")
    @Expose
    private String billing_name;

    @SerializedName("order_status")
    @Expose
    private String order_status;

    @SerializedName("payment_method")
    @Expose
    private String payment_method;

    @SerializedName("billing_address")
    @Expose
    private Integer billing_address;

    @SerializedName("shipping_address")
    @Expose
    private Integer shipping_address;

    @SerializedName("delivery_fee")
    @Expose
    private Double delivery_fee;

    @SerializedName("order_quantity")
    @Expose
    private Double order_quantity;

    @SerializedName("order_amount")
    @Expose
    private Double order_amount;

    @SerializedName("tax_percent")
    @Expose
    private Double tax_percent;

    @SerializedName("coupon_code")
    @Expose
    private String coupon_code;

    @SerializedName("discount_percent")
    @Expose
    private Double discount_percent;

    @SerializedName("flat_discount_amount")
    @Expose
    private Double flat_discount_amount;

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
    private List<Order_Items> order_items = null;

    public String getBp_sales_order_id() {
        return bp_sales_order_id;
    }

    public void setBp_sales_order_id(String bp_sales_order_id) {
        this.bp_sales_order_id = bp_sales_order_id;
    }

    public String getExpected_delivery_date() {
        return expected_delivery_date;
    }

    public void setExpected_delivery_date(String expected_delivery_date) {
        this.expected_delivery_date = expected_delivery_date;
    }

    public String getBilling_name() {
        return billing_name;
    }

    public void setBilling_name(String billing_name) {
        this.billing_name = billing_name;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
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

    public Double getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(Double delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public Double getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(Double order_quantity) {
        this.order_quantity = order_quantity;
    }

    public Double getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(Double order_amount) {
        this.order_amount = order_amount;
    }

    public Double getTax_percent() {
        return tax_percent;
    }

    public void setTax_percent(Double tax_percent) {
        this.tax_percent = tax_percent;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public Double getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(Double discount_percent) {
        this.discount_percent = discount_percent;
    }

    public Double getFlat_discount_amount() {
        return flat_discount_amount;
    }

    public void setFlat_discount_amount(Double flat_discount_amount) {
        this.flat_discount_amount = flat_discount_amount;
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

    public List<Order_Items> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(List<Order_Items> order_items) {
        this.order_items = order_items;
    }
}
