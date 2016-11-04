package ru.seleand.restaurants.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.repository.DishRepository;
import ru.seleand.restaurants.repository.mock.DishRepositoryImpl;
import ru.seleand.restaurants.util.DishUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Asus on 01.11.2016.
 */
public class DishServlet extends javax.servlet.http.HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DishServlet.class);
    private DishRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new DishRepositoryImpl();
        repository.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        int restaurantId = getParameterInt(request,"restaurantId");
        double priceDouble = Double.valueOf(request.getParameter("price"))*100;
        int price = (int) priceDouble;
        Dish dish= new Dish(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDate.parse(request.getParameter("date")),
                request.getParameter("description"),
                price,
                restaurantId);

        LOG.info(dish.isNew() ? "Create {}" : "Update {}", dish);
        repository.save(dish, restaurantId);
//        request.setAttribute("restaurantId",restaurantId);
        response.sendRedirect("dishes?restaurantId="+restaurantId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int restaurantId = getParameterInt(request,"restaurantId");

        if (action == null) {
            LOG.info("getAll, restaurantId {}", restaurantId);
            request.setAttribute("dishList",
                    repository.getAll(restaurantId));
            request.setAttribute("restaurantId",restaurantId);
            request.getRequestDispatcher("/dishList.jsp").forward(request, response);

        } else if ("delete".equals(action)) {
            int id = getParameterInt(request, "id");
            LOG.info("Delete {}, restaurantId {}", id, restaurantId);
            repository.delete(id, restaurantId);
            response.sendRedirect("dishes?restaurantId="+restaurantId);

        } else if ("create".equals(action) || "update".equals(action)) {
            final Dish dish = action.equals("create") ?
                    new Dish(LocalDate.now(), restaurantId) :
                    repository.get(getParameterInt(request, "id"), restaurantId);
            request.setAttribute("dish", dish);
            request.getRequestDispatcher("dishEdit.jsp").forward(request, response);
        }
    }

    private int getParameterInt(HttpServletRequest request, String parameterName) {
        String param = Objects.requireNonNull(request.getParameter(parameterName));
        return Integer.valueOf(param);
    }
}
