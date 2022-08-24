package com.codecool.shop.service.product;

import com.codecool.shop.model.dto.ProductDTO;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.product.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public ProductCategory getProductCategory(int categoryId);

    public List<Product> getProductsByCategory(int categoryId);

    public List<Product> getProductsBySupplier(int supplierId);

    public Product getProductById(int productId);
    public void addToCart(ProductDTO productDTO);
    public void removeFromCart(ProductDTO productDTO);
    public Optional<ProductDTO> getProductDTOById(String id);
}
