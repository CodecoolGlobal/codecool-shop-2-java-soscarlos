package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public class OrderDaoJdbc implements CartDao {
    @Override
    public void addToCart(ProductDTO product) {

    }

    @Override
    public void removeFromCart(ProductDTO product) {

    }

    @Override
    public Optional<ProductDTO> getProductDTOById(String id) {
        return Optional.empty();
    }

    @Override
    public List<ProductDTO> getProductsDTO() {
        return null;
    }
}
