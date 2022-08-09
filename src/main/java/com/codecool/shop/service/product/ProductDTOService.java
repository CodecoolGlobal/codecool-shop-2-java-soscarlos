package com.codecool.shop.service.product;

import com.codecool.shop.model.dto.ProductDTO;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.service.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDTOService {
    private final Mapper mapper;

    public ProductDTOService() {
        this.mapper = new Mapper();
    }

    public List<ProductDTO> getProducts(List<Product> products) {
        return products.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
