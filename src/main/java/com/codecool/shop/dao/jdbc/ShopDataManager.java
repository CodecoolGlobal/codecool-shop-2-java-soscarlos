package com.codecool.shop.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ShopDataManager {
    private static final Logger logger = LoggerFactory.getLogger(ShopDataManager.class);
    public void setUp(){
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/connection.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);

            String dbName = (String) properties.get("database");
            String url = (String) properties.get("url");
            String user = (String) properties.get("user");
            String password = (String) properties.get("password");
            String dao = (String) properties.get("dao");
            System.out.printf("%s %s %s %s %s", dbName, url, user, password, dao);

        } catch (FileNotFoundException e) {
            logger.error("could not find the file", new RuntimeException(e));
        } catch (IOException e) {
            logger.error("could not load file", new RuntimeException(e));
        }


    }
}
