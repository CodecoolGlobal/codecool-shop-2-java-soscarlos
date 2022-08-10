package com.codecool.shop.controller.api;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
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

@WebServlet(urlPatterns = {"/api/products/category"})
public abstract class CategoryServlet extends javax.servlet.http.HttpServlet {
    protected List<Product> productsList;
    private ProductService productService;
    private String parameter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDTOService productDTOService = new ProductDTOService();
        parameter = req.getParameter("id");

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);

        setProductsList(productService, parameter);
        List<Product> products = getProductsList();
        List<ProductDTO> productsDTO = productDTOService.getProducts(products);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonResponse = objectMapper.writeValueAsString(productsDTO);
        System.out.println(jsonResponse);


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    public String getParameter() {
        return parameter;
    }

    public ProductService getProductService() {
        return productService;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(ProductService productService, String parameter) {
        this.productsList = productService.getProductsByCategory(Integer.parseInt(parameter));
    }
}
