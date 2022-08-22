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
import java.util.Optional;

@WebServlet(urlPatterns = {"/api/cart"})
public class CartApiServlet extends javax.servlet.http.HttpServlet {
    private final ServletService service = new ServletService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = request.getQueryString();
        Product productForCart;
        ProductService productService = service.getProductService();
        CartDao cartDao = CartDao.getInstance();

        if (query == null) {
            List<ProductDTO> productsDTO = cartDao.getProductsDTO();
            service.sendJsonResponse(productsDTO, response);
        } else {
            String[] split = query.split("=");
            String key = split[0];
            String value = split[1];
            productForCart = productService.getProductById(Integer.parseInt(value));
            ProductDTO productDTO = service.getProductDto(productForCart);
            if(key.equals("addId")) {
                cartDao.addToCart(productDTO);
            } else if (key.equals("removeId")){
                Optional<ProductDTO> productDTOOptional = cartDao.getProductDTOById(value);
                productDTOOptional.ifPresent(cartDao::removeFromCart);
            }
            List<ProductDTO> productsDTO = cartDao.getProductsDTO();
            service.sendJsonResponse(productsDTO, response);
        }
    }
}
