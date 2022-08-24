package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.jdbc.*;
import com.codecool.shop.model.dto.ProductDTO;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.product.ProductCategory;
import com.codecool.shop.model.product.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        List<ProductCategory> categories = null;
        List<Product> products = null;
        List<Supplier> suppliers = null;
        List<ProductDTO> productDTOs = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/connection.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);

            String dao = (String) properties.get("dao");
            String memory = "memory";
            String jdbc = "jdbc";

            if (dao.equals(memory)) {
                System.out.println("Load from memory");
                ProductDao productDataStore = ProductDaoMem.getInstance();
                ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
                SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
                CartDao cartDao = CartDao.getInstance();

                categories = productCategoryDataStore.getAll();
                products = productDataStore.getAll();
                suppliers = supplierDataStore.getAll();
                productDTOs = cartDao.getProductsDTO();

            } else if (dao.equals(jdbc)) {
                System.out.println("Load from db");
                ShopDataManager dbManager = ShopDataManager.getInstance();
                DataSource dataSource = dbManager.connect();
                ProductCategoryDaoJdbc categoryDaoJdbc = ProductCategoryDaoJdbc.getInstance(dataSource);
                ProductDaoJdbc productDaoJdbc = ProductDaoJdbc.getInstance(dataSource);
                SupplierDaoJdbc supplierDaoJdbc = SupplierDaoJdbc.getInstance(dataSource);
                OrderDaoJdbc orderDaoJdbc = OrderDaoJdbc.getInstance(dataSource);

                categories = categoryDaoJdbc.getAll();
                products = productDaoJdbc.getAll();
                suppliers = supplierDaoJdbc.getAll();
                productDTOs = orderDaoJdbc.getProducts();
            }
        } catch (FileNotFoundException e) {
            logger.error("Could not find file", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Could not read from file stream", e);
            throw new RuntimeException(e);
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("categories", categories);
        context.setVariable("products", products);
        context.setVariable("suppliers", suppliers);
        context.setVariable("cartProducts", productDTOs);

        try {
            engine.process("product/index.html", context, resp.getWriter());
        } catch (IOException e) {
            logger.error("Error by attempting to write servlet response {} ", resp, new Throwable(e));
        }

    }

}
