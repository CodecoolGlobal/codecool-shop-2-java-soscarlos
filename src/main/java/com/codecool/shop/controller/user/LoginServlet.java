package com.codecool.shop.controller.user;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.controller.api.ServletService;
import com.codecool.shop.dao.jdbc.ShopDataManager;
import com.codecool.shop.dao.jdbc.UserDaoJdbc;
import com.codecool.shop.model.user.User;
import com.codecool.shop.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends javax.servlet.http.HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ServletService.class);
    private final ShopDataManager dbManager = ShopDataManager.getInstance();
    private final DataSource dataSource = dbManager.connect();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        try {
            engine.process("user/login.html", context, resp.getWriter());
        } catch (IOException e) {
            logger.error("Error by trying to write servlet response", new Throwable(e));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String error = "";
        if (RegistrationService.emailMatchesPassword(email, password)) {
            resp.sendRedirect("/");
        } else {
            error = "Wrong username or password!";
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("error", error);
            engine.process("user/login.html", context, resp.getWriter());
        }
    }
}