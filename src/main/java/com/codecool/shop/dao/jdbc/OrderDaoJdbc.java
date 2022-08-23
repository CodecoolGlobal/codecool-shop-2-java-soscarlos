package com.codecool.shop.dao.jdbc;

import com.codecool.shop.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class OrderDaoJdbc {

    private static final Logger logger = LoggerFactory.getLogger(ProductDaoJdbc.class);

    private final DataSource dataSource;
    private static OrderDaoJdbc instance = null;

    private OrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static OrderDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new OrderDaoJdbc(dataSource);
        }
        return instance;
    }

    public void addToCart(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO orders (product_id) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, product.getId());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            logger.error("Could not connect with database", e);
            throw new RuntimeException(e);
        }
    }

    public void removeFromCart(Product product) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM orders WHERE product_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Could not connect with database", e);
            throw new RuntimeException(e);
        }
    }


    public Optional<Product> getProductById(String id) {
        return Optional.empty();
    }


    public List<Product> getProducts() {

        return null;
    }
}
