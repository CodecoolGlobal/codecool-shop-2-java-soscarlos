package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.controller.api.ServletService;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.jdbc.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.jdbc.ShopDataManager;
import com.codecool.shop.model.product.ProductCategory;
import org.postgresql.ds.PGSimpleDataSource;
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
import java.util.Properties;
import java.util.SortedMap;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        CartDao cartDao = CartDao.getInstance();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/connection.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);

            String dao = (String) properties.get("dao");
            String memory = "memory";
            String jdbc = "jdbc";

            if (dao.equals(memory)){
                System.out.println("Load from memory");
            } else if (dao.equals(jdbc)) {
                System.out.println("Load from db");
            }

        } catch (FileNotFoundException e){
            logger.error("Could not find file", e);
            throw new RuntimeException(e);
        } catch (IOException e){
            logger.error("Could not read from file stream", e);
            throw new RuntimeException(e);
        }

        ShopDataManager dbManager = ShopDataManager.getInstance();

        DataSource dataSource = dbManager.connect();

        ProductCategoryDaoJdbc categoryDaoJdbc = ProductCategoryDaoJdbc.getInstance(dataSource);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("products", productDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("cartProducts", cartDao.getProductsDTO());
        try {
            engine.process("product/index.html", context, resp.getWriter());
        } catch (IOException e) {
            logger.error("Error by attempting to write servlet response {} ", resp, new Throwable(e));
        }

    }

}
