package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.product.ProductCategory;
import com.codecool.shop.model.product.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier mac = new Supplier("Mac", "Computers");
        supplierDataStore.add(mac);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);

        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A mobile phone is a portable device for connecting to a telecommunications network in order to transmit and receive voice, video, or other data.");
        productCategoryDataStore.add(phone);

        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "Laptops are computers designed for portability. They have all the same components and capabilities of traditional desktops, but are smaller and can fold up.");
        productCategoryDataStore.add(laptop);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

        productDataStore.add(new Product("Iphone X", new BigDecimal("1000"), "EUR", "bla bla bla", phone, mac));
        productDataStore.add(new Product("Iphone 7", new BigDecimal("1000"), "EUR", "bla bla bla", phone, mac));
        productDataStore.add(new Product("Iphone 11", new BigDecimal("1000"), "EUR", "bla bla bla", phone, mac));

        productDataStore.add(new Product("macbook air", new BigDecimal("1200"), "EUR", "bla bla bla", laptop, mac));
        productDataStore.add(new Product("macbook pro", new BigDecimal("1400"), "EUR", "bla bla bla", laptop, mac));
        productDataStore.add(new Product("macbook no pro", new BigDecimal("900"), "EUR", "bla bla bla", laptop, mac));

    }
}
