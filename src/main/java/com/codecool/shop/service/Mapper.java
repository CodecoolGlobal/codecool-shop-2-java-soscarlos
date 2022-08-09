package com.codecool.shop.service;

import com.codecool.shop.model.dto.ProductDTO;
import com.codecool.shop.model.product.Product;

import java.util.List;

public class Mapper {
    public ProductDTO toDto(Product product) {
        String name = product.getName();
        String defaultPrice = String.valueOf(product.getDefaultPrice());
        String description = product.getDescription();
        String productCategory = product.getProductCategory().getName();
        String supplier = product.getSupplier().getName();

        return new ProductDTO(name, defaultPrice, description, productCategory, supplier);
    }
}
