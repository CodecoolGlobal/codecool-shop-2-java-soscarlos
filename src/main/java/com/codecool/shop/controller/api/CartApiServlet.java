package com.codecool.shop.controller.api;

import com.codecool.shop.dao.implementation.CartDao;
import com.codecool.shop.model.dto.ProductDTO;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.service.product.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/cart"})
public class CartApiServlet extends javax.servlet.http.HttpServlet {
    private final ServletService service = new ServletService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String parameter = request.getParameter("id");
        Product productForCart;
        ProductService productService = service.getProductService();
        CartDao cartDao = CartDao.getInstance();

        if (parameter == null) {
            List<ProductDTO> productsDTO = cartDao.getProductsDTO();
            service.sendJsonResponse(productsDTO, response);
        } else {
            productForCart = productService.getProductById(Integer.parseInt(parameter));
            ProductDTO productDTO = service.getProductDto(productForCart);

            cartDao.addToCart(productDTO);

            List<ProductDTO> productsDTO = cartDao.getProductsDTO();
            service.sendJsonResponse(productsDTO, response);
        }
    }
}
