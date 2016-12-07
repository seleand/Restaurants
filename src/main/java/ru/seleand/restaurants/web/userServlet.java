package ru.seleand.restaurants.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.seleand.restaurants.AuthorizedUser;
import ru.seleand.restaurants.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Asus on 10.11.2016.
 */
public class userServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(userServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        AuthorizedUser.setMainRole(Role.ROLE_ADMIN);
        response.sendRedirect("restaurants");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
