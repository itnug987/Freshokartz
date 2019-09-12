
package com.freshokartz2.Product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("hsn")
    @Expose
    private Object hsn;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("product_hindi_name")
    @Expose
    private String productHindiName;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("new_product")
    @Expose
    private Boolean newProduct;
    @SerializedName("featured_product")
    @Expose
    private Boolean featuredProduct;
    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("product_status")
    @Expose
    private Boolean productStatus;
    @SerializedName("out_of_stock_threshold")
    @Expose
    private Integer outOfStockThreshold;
    @SerializedName("notify_for_quantity_below")
    @Expose
    private Integer notifyForQuantityBelow;
    @SerializedName("minimum_sale_quantity")
    @Expose
    private String minimumSaleQuantity;
    @SerializedName("maximum_sale_quantity")
    @Expose
    private String maximumSaleQuantity;
    @SerializedName("qty_increment")
    @Expose
    private String qtyIncrement;
    @SerializedName("in_stock")
    @Expose
    private Boolean inStock;
    @SerializedName("price")
    @Expose
    private Double price;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Object getHsn() {
        return hsn;
    }

    public void setHsn(Object hsn) {
        this.hsn = hsn;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getProductHindiName() {
        return productHindiName;
    }

    public void setProductHindiName(String productHindiName) {
        this.productHindiName = productHindiName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Boolean newProduct) {
        this.newProduct = newProduct;
    }

    public Boolean getFeaturedProduct() {
        return featuredProduct;
    }

    public void setFeaturedProduct(Boolean featuredProduct) {
        this.featuredProduct = featuredProduct;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Boolean getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Boolean productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getOutOfStockThreshold() {
        return outOfStockThreshold;
    }

    public void setOutOfStockThreshold(Integer outOfStockThreshold) {
        this.outOfStockThreshold = outOfStockThreshold;
    }

    public Integer getNotifyForQuantityBelow() {
        return notifyForQuantityBelow;
    }

    public void setNotifyForQuantityBelow(Integer notifyForQuantityBelow) {
        this.notifyForQuantityBelow = notifyForQuantityBelow;
    }

    public String getMinimumSaleQuantity() {
        return minimumSaleQuantity;
    }

    public void setMinimumSaleQuantity(String minimumSaleQuantity) {
        this.minimumSaleQuantity = minimumSaleQuantity;
    }

    public String getMaximumSaleQuantity() {
        return maximumSaleQuantity;
    }

    public void setMaximumSaleQuantity(String maximumSaleQuantity) {
        this.maximumSaleQuantity = maximumSaleQuantity;
    }

    public String getQtyIncrement() {
        return qtyIncrement;
    }

    public void setQtyIncrement(String qtyIncrement) {
        this.qtyIncrement = qtyIncrement;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
