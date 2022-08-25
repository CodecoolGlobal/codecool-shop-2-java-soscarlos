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

    public static boolean emailMatchesPassword(String email, String password) {
        boolean answer = false;
        List<User> users = UserDaoJdbc.getInstance(dataSource).getAll();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                int userId = user.getId();
                User existingUser = UserDaoJdbc.getInstance(dataSource).find(userId);
                answer = PasswordService.checkPassword(password, existingUser.getPassword());
            }
        }
        return answer;
    }

    public static boolean emailAlreadyExists(String email) {
        List<User> users = UserDaoJdbc.getInstance(dataSource).getAll();
        return users.stream()
                .anyMatch(u -> u.getEmail().equals(email));
    }
}
