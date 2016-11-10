package ru.seleand.restaurants.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.service.DishService;

import java.util.List;

@Controller
public class DishAdminRestController {
    private static final Logger LOG = LoggerFactory.getLogger(DishAdminRestController.class);

    @Autowired
    private DishService service;

    public Dish save(Dish dish, int restaurantId) {
        LOG.info("Save dish {} for restaurant with id {}",dish,restaurantId);
        return service.save(dish, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        LOG.info("Delete dish by id {} for restaurant with id {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    public Dish get(int id, int restaurantId) {
        LOG.info("Get dish by id {} for restaurant with id {}",id, restaurantId);
        return service.get(id, restaurantId);
    }

    public List<Dish> getAll(int restaurantId) {
        LOG.info("Get all dishes for restaurant with id {}", restaurantId);
        return service.getAll(restaurantId);
    }

    public void update(Dish dish, int restaurantId) {
        LOG.info("Update dish {} for reastaurant with id {}", dish, restaurantId);
        service.update(dish, restaurantId);
    }
}
