package com.codecool.shop.controller.api;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CartDao;
import com.codecool.shop.model.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/cart"})
public class CartServlet extends javax.servlet.http.HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ServletService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        CartDao cartDao = CartDao.getInstance();
        List<ProductDTO> productsInCart = cartDao.getProductsDTO();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("cartProducts", productsInCart);
        try {
            engine.process("product/cart.html", context, resp.getWriter());
        } catch (IOException e) {
            logger.error("Error by trying to write servlet response", new Throwable(e));
        }
    }
}