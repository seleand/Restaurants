package ru.seleand.restaurants.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.service.RestaurantService;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Asus on 05.11.2016.
 */
@Controller
public class RestaurantAdminRestController {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantAdminRestController.class);

    @Autowired
    private RestaurantService service;

    public Restaurant save(Restaurant restaurant){
        LOG.info("Save restaurant {}", restaurant);
        return service.save(restaurant);
    }

    public void delete(int id) {
        LOG.info("Delete restaurant by id {}",id);
        service.delete(id);
    }

    public Restaurant get(int id) {
        LOG.info("Get restaurant by id {}", id);
        return service.get(id);
    }

    public List<Restaurant> getAll() {
        LOG.info("Get all restaurants");
        return service.getAll();
    }

    public void update(Restaurant restaurant) {
        LOG.info("Update restaursnt {}",restaurant);
        service.update(restaurant);
    }

}
