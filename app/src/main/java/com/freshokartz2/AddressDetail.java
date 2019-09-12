package com.freshokartz2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressDetail {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("house_flat_shop_number")
    @Expose
    private String house_flat_shop_number;
    @SerializedName("detail_address")
    @Expose
    private String detail_address;
    @SerializedName("area")
    @Expose
    private int area;
    @SerializedName("city")
    @Expose
    private int city;
    @SerializedName("state")
    @Expose
    private int state;
    @SerializedName("country")
    @Expose
    private int country;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("contact_number")
    @Expose
    private String contact_number;
    @SerializedName("is_default_billing")
    @Expose
    private Boolean is_default_billing;
    @SerializedName("is_default_shipping")
    @Expose
    private Boolean is_default_shipping;

    public AddressDetail(String name, String nickname, String house_flat_shop_number, String detail_address, int area, int city, int state, int country, String pincode, String contact_number, Boolean is_default_billing, Boolean is_default_shipping) {
        this.name = name;
        this.nickname = nickname;
        this.house_flat_shop_number = house_flat_shop_number;
        this.detail_address = detail_address;
        this.area = area;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.contact_number = contact_number;
        this.is_default_billing = is_default_billing;
        this.is_default_shipping = is_default_shipping;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHouse_flat_shop_number() {
        return house_flat_shop_number;
    }

    public void setHouse_flat_shop_number(String house_flat_shop_number) {
        this.house_flat_shop_number = house_flat_shop_number;
    }

    public String getDetail_address() {
        return detail_address;
    }

    public void setDetail_address(String detail_address) {
        this.detail_address = detail_address;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public Boolean getIs_default_billing() {
        return is_default_billing;
    }

    public void setIs_default_billing(Boolean is_default_billing) {
        this.is_default_billing = is_default_billing;
    }

    public Boolean getIs_default_shipping() {
        return is_default_shipping;
    }

    public void setIs_default_shipping(Boolean is_default_shipping) {
        this.is_default_shipping = is_default_shipping;
    }
}
