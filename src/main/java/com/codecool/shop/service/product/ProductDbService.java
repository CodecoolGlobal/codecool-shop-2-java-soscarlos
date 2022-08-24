package com.codecool.shop.service.product;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.dto.ProductDTO;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.product.ProductCategory;

import java.util.List;
import java.util.Optional;

public class ProductDbService implements ProductService {
    private ProductDao productDaoJdbc;
    private ProductCategoryDao productCategoryDaoJdbc;
    private SupplierDao supplierDaoJdbc;
    private CartDao orderDao;

    public ProductDbService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao, CartDao orderDao) {
        this.productDaoJdbc = productDao;
        this.productCategoryDaoJdbc = productCategoryDao;
        this.supplierDaoJdbc = supplierDao;
        this.orderDao = orderDao;
    }

    public ProductCategory getProductCategory(int categoryId) {
        return productCategoryDaoJdbc.find(categoryId);
    }

    public List<Product> getProductsByCategory(int categoryId) {
        var category = getProductCategory(categoryId);
        return productDaoJdbc.getBy(category);
    }

    public List<Product> getProductsBySupplier(int supplierId) {
        var supplier = supplierDaoJdbc.find(supplierId);
        return productDaoJdbc.getBy(supplier);
    }

    public Product getProductById(int productId) {
        return productDaoJdbc.find(productId);
    }

    public void addToCart(ProductDTO productDTO){
        orderDao.addToCart(productDTO);
    }

    public void removeFromCart(ProductDTO productDTO) {
        orderDao.removeFromCart(productDTO);
    }

    public Optional<ProductDTO> getProductDTOById(String id) {
        return orderDao.getProductDTOById(id);
    }
}
