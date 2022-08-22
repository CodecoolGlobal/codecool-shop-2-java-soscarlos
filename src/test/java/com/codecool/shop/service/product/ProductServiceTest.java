package com.codecool.shop.service.product;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ProductServiceTest {

    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    @BeforeEach
    void initialize() {
//        TODO mock all DAO classes needed to test ProductService Class
    }

    @Test
    void getProductCategory_existingId_returnsProductCategory() {
        //    TODO implement this a 2 other tests methods for the getProductCategory method
    }

    @Test
    void getProductsByCategory_existingCategoryId_returnsListOfProducts() {
        //    TODO implement this a 2 other tests methods for the getProductsByCategory method
    }

    @Test
    void getProductsBySupplier_existingSupplierId_returnsListOfProducts() {
        //    TODO implement this a 2 other tests methods for the getProductsBySupplier method
    }

    @Test
    void getProductById_existingProductId_returnsProduct() {
        //    TODO implement this a 2 other tests methods for the getProductById method
    }
}
