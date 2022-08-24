package com.codecool.shop.controller.api;

import com.codecool.shop.controller.LoadService;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.dto.ProductDTO;
import com.codecool.shop.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/api/cart"})
public class CartApiServlet extends javax.servlet.http.HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CartApiServlet.class);
    private final ServletService service = new ServletService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String query = request.getQueryString();

        Product productForCart;

        LoadService load = LoadService.getInstance(logger);

//        CartDaoMem cartDaoMem = CartDaoMem.getInstance();

        if (query == null) {
            List<ProductDTO> productsDTO = load.getAllProductDTOs();
            service.sendJsonResponse(productsDTO, response);
        } else {
            String[] split = query.split("=");
            String key = split[0];
            String value = split[1];
            productForCart = service.getProductByIdByDao(Integer.parseInt(value));
            ProductDTO productDTO = service.getProductDto(productForCart);
            if (key.equals("addId")) {
//                cartDaoMem.addToCart(productDTO);
                service.addToCartByDao(productDTO);
            } else if (key.equals("removeId")) {
//                Optional<ProductDTO> productDTOOptional = cartDaoMem.getProductDTOById(value);
                Optional<ProductDTO> productDTOOptional = service.getProductDTOByIdByDao(value);

                productDTOOptional.ifPresent(service::removeFromCartByDao);
            }
            List<ProductDTO> productsDTO = load.getAllProductDTOs();
            service.sendJsonResponse(productsDTO, response);
        }
    }
}
