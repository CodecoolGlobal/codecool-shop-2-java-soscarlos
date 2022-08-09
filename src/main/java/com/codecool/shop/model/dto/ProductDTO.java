package com.codecool.shop.model.dto;

import java.util.List;

public class ProductDTO {
    private String name;
    private String defaultPrice;
    private String description;
    private String productCategory;
    private String supplier;

    public ProductDTO(String name, String defaultPrice, String description, String productCategory, String supplier) {
        this.name = name;
        this.defaultPrice = defaultPrice;
        this.description = description;
        this.productCategory = productCategory;
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(String defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
