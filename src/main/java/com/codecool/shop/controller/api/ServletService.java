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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ServletService {

    private static final Logger logger = LoggerFactory.getLogger(ServletService.class);
    private final ProductDTOService productDTOService = new ProductDTOService();
    public ProductService getProductService() {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        return new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);
    }

    public List<ProductDTO> getProductsDto(List<Product> products) {
        return productDTOService.getProducts(products);
    }

    public ProductDTO getProductDto(Product product){
        return productDTOService.getProduct(product);
    }

    public void sendJsonResponse(List<ProductDTO> productsDTO, HttpServletResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String jsonResponse = objectMapper.writeValueAsString(productsDTO);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonResponse);
            out.flush();
        } catch (IOException e){
            logger.error("Cannot write Json Response", e);
            throw new RuntimeException(e);
        }
    }

}
