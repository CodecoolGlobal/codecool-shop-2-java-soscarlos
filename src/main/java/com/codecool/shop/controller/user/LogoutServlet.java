package com.codecool.shop.controller.user;

import com.codecool.shop.controller.api.ServletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet  extends javax.servlet.http.HttpServlet{
    private static final Logger logger = LoggerFactory.getLogger(ServletService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getSession(false).invalidate();
            System.out.println(req.getSession(false));
            resp.sendRedirect("/");
        } catch (IOException e) {
            logger.error("Error by trying to write servlet response", new Throwable(e));
        }
    }
}
