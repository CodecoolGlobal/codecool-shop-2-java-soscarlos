package com.codecool.shop.service;

import com.codecool.shop.dao.jdbc.ShopDataManager;
import com.codecool.shop.dao.jdbc.UserDaoJdbc;
import com.codecool.shop.model.user.User;

import javax.sql.DataSource;
import java.util.List;

public class RegistrationService {
    private static final ShopDataManager dbManager = ShopDataManager.getInstance();
    private static final DataSource dataSource = dbManager.connect();

    public static boolean userAlreadyExists(User user) {
        List<User> users = UserDaoJdbc.getInstance(dataSource).getAll();
        return users.stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()));
    }

    // TODO
    public static boolean emailMatchesPassword(String email, String password) {
        if (emailAlreadyExists(email)) {
            return true;
        }
        return false;
    }

    public static boolean emailAlreadyExists(String email) {
        List<User> users = UserDaoJdbc.getInstance(dataSource).getAll();
        return users.stream()
                .anyMatch(u -> u.getEmail().equals(email));
    }
}
