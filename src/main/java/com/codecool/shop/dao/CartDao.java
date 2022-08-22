package com.codecool.shop.dao;

import com.codecool.shop.model.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface CartDao {
    public void addToCart(ProductDTO product);

    public void removeFromCart(ProductDTO product);

    public Optional<ProductDTO> getProductDTOById(String id);

    public List<ProductDTO> getProductsDTO();
}
