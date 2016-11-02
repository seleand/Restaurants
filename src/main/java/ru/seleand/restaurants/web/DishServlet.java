package ru.seleand.restaurants.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.seleand.restaurants.util.DishUtil;

import java.io.IOException;

/**
 * Created by Asus on 01.11.2016.
 */
public class DishServlet extends javax.servlet.http.HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DishServlet.class);
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        LOG.debug("redirect to dishList");
        request.setAttribute("dishList", DishUtil.DISHES);
        request.getRequestDispatcher("/dishList.jsp").forward(request, response);

    }
}
