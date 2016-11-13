package ru.seleand.restaurants.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.repository.RestaurantRepository;
import ru.seleand.restaurants.web.restaurant.RestaurantAdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by Asus on 01.11.2016.
 */
public class RestaurantServlet extends javax.servlet.http.HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantServlet.class);
//    private RestaurantRepository repository;

    private ConfigurableApplicationContext springContext;
    private RestaurantAdminRestController restController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/spring-db.xml");
        restController = springContext.getBean(RestaurantAdminRestController.class);
//        RestaurantRepository repository = springContext.getBean(RestaurantRepositoryImpl.class);
//        repository.init();
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

/*
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new RestaurantRepositoryImpl();
        repository.init();
    }
*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Restaurant restaurant= new Restaurant(id.isEmpty() ? null : Integer.valueOf(id),
                request.getParameter("name"));

        LOG.info(restaurant.isNew() ? "Create {}" : "Update {}", restaurant);
        restController.save(restaurant);
        response.sendRedirect("restaurants");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("restaurantList",
                    restController.getAll());
            request.getRequestDispatcher("/restaurantList.jsp").forward(request, response);

        } else if ("delete".equals(action)) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            restController.delete(id);
            response.sendRedirect("restaurants");

        } else if ("create".equals(action) || "update".equals(action)) {
            final Restaurant restaurant= action.equals("create") ?
                    new Restaurant() :
                    restController.get(getId(request));
            request.setAttribute("restaurant", restaurant);
            request.getRequestDispatcher("restaurantEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
