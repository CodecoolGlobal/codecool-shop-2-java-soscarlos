package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartDaoMem implements com.codecool.shop.dao.CartDao {
    private final List<ProductDTO> productsDTO = new ArrayList<>();

    private static CartDaoMem instance = null;

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    public void addToCart(ProductDTO product){
        productsDTO.add(product);
    }
    public void removeFromCart(ProductDTO product){
        productsDTO.remove(product);
    }

    public Optional<ProductDTO> getProductDTOById(String id){
        return productsDTO.stream().filter(productDTO -> productDTO.getId().equals(id)).findFirst();
    }

    public List<ProductDTO> getProductsDTO() {
        return productsDTO;
    }
}
