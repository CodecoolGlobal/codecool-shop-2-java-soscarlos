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
import java.math.BigDecimal;
import java.util.List;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentServlet extends javax.servlet.http.HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ServletService.class);
    private final CartDao cartDao = CartDao.getInstance();
    private final List<ProductDTO> productsInCart = cartDao.getProductsDTO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        BigDecimal totalPrice = calculateTotalPrice();
        context.setVariable("cartProducts", productsInCart);
        context.setVariable("totalPrice", totalPrice);
        try {
            engine.process("product/payment.html", context, resp.getWriter());
        } catch (IOException e) {
            logger.error("Error by trying to write servlet response", new Throwable(e));
        }
    }

    private BigDecimal calculateTotalPrice() {
        BigDecimal sum = new BigDecimal(0);
        for (ProductDTO productDTO : productsInCart) {
            sum = sum.add(BigDecimal.valueOf(Double.parseDouble(productDTO.getDefaultPrice())));
        }
        return sum;
    }
}