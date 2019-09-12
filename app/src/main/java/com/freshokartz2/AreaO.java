package com.freshokartz2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AreaO {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("city")
    @Expose
    private Integer city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

}