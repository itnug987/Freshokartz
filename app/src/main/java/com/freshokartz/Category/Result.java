
package com.freshokartz.Category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("category_tree_id")
    @Expose
    private Integer categoryTreeId;
    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Integer getCategoryTreeId() {
        return categoryTreeId;
    }

    public void setCategoryTreeId(Integer categoryTreeId) {
        this.categoryTreeId = categoryTreeId;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
