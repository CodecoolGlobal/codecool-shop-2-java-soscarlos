package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
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
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        LoadService load = LoadService.getInstance(logger);

        List<ProductCategory> categories = load.getCategories();
        List<Product> products = load.getAllProducts();
        List<Supplier> suppliers = load.getAllSuppliers();
        List<ProductDTO> productDTOs = load.getAllProductDTOs();

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
