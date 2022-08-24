package com.codecool.shop.controller.user;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.controller.api.ServletService;
import com.codecool.shop.dao.jdbc.ShopDataManager;
import com.codecool.shop.dao.jdbc.UserDaoJdbc;
import com.codecool.shop.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;


@WebServlet(urlPatterns = {"/register"})
public class RegistrationServlet extends javax.servlet.http.HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ServletService.class);
    private final ShopDataManager dbManager = ShopDataManager.getInstance();
    private final DataSource dataSource = dbManager.connect();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        try {
            engine.process("user/register.html", context, resp.getWriter());
        } catch (IOException e) {
            logger.error("Error by trying to write servlet response", new Throwable(e));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User(username, email, password);
        UserDaoJdbc.getInstance(dataSource).add(user);
        resp.sendRedirect("/");
    }
}