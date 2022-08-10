package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class CartDao {
    private final List<ProductDTO> productsDTO = new ArrayList<>();

    private static CartDao instance = null;

    private CartDao() {
    }

    public static CartDao getInstance() {
        if (instance == null) {
            instance = new CartDao();
        }
        return instance;
    }

    public void addToCart(ProductDTO product){
        productsDTO.add(product);
    }

    public List<ProductDTO> getProductsDTO() {
        return productsDTO;
    }
}
