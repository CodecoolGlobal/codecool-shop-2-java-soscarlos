package com.codecool.shop.controller.api;

import com.codecool.shop.dao.jdbc.OrderDaoJdbc;
import com.codecool.shop.dao.jdbc.ShopDataManager;
import com.codecool.shop.model.dto.OrderDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = {"/api/order"})
public class OrderApiServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(OrderApiServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        ShopDataManager dbManager = ShopDataManager.getInstance();
        DataSource dataSource = dbManager.connect();
        OrderDaoJdbc orderDaoJdbc = OrderDaoJdbc.getInstance(dataSource);

        StringBuilder builder = new StringBuilder();

        String line;

        JSONObject jsonObject;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) builder.append(line);

            jsonObject = new JSONObject(builder.toString());

        } catch (Exception e) {
            logger.error("Could not read line of json request", e);
            throw new RuntimeException(e);
        }

        int userId = jsonObject.getInt("user_id");
        int quantity = jsonObject.getInt("quantity");
        BigDecimal total = jsonObject.getBigDecimal("total");
        String StringStatus = jsonObject.getString("status");

        OrderDTO order = new OrderDTO(userId, quantity, total, StringStatus);

        orderDaoJdbc.checkOutOrder(order);

        System.out.println(jsonObject);

    }
}
