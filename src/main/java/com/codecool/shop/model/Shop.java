package com.codecool.shop.model;

import com.codecool.shop.model.product.ProductCategory;

import java.util.Set;

public class Shop {
    private final Set<ProductCategory> categories;


    public Shop(Set<ProductCategory> categories) {
        this.categories = categories;
        System.out.println(categories);
    }

    public Set<ProductCategory> getCategories() {
        return categories;
    }
}
