package com.codecool.shop.controller.api;

import com.codecool.shop.model.dto.ProductDTO;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.service.product.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = {"/api/products/supplier"})
public class SupplierServlet extends HttpServlet {
    private final ServletService service = new ServletService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String parameter = req.getParameter("id");
        ProductService productService = service.getProductService();
        List<Product> products = productService.getProductsBySupplier(Integer.parseInt(parameter));
        List<ProductDTO> productsDTO = service.getProductsDto(products);

        service.sendJsonResponse(productsDTO, resp);
    }
}
