package com.codecool.shop.controller.api;

import com.codecool.shop.service.product.ProductService;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/api/products/supplier"})
public class SupplierServlet extends CategoryServlet{

    public SupplierServlet() {
        setProductsList(getProductService(), getParameter());
    }
    @Override
    public void setProductsList(ProductService productService, String parameter) {
        this.productsList = productService.getProductsBySupplier(Integer.parseInt(parameter));
    }
}
