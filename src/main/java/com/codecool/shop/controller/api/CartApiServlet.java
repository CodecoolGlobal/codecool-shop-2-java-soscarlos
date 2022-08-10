package com.codecool.shop.controller.api;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.dto.ProductDTO;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.service.product.ProductDTOService;
import com.codecool.shop.service.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/api/cart"})
public class CartApiServlet extends javax.servlet.http.HttpServlet {
    private final ServletService service = new ServletService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String parameter = request.getParameter("id");

        ProductService productService = service.getProductService();
        Product productForCart = productService.getProductById(Integer.parseInt(parameter));
        ProductDTO productDTO = service.getProductDto(productForCart);

        CartDao cartDao = CartDao.getInstance();
        cartDao.addToCart(productDTO);

        List<ProductDTO> productsDTO = cartDao.getProductsDTO();
        service.sendJsonResponse(productsDTO, response);
    }
}
