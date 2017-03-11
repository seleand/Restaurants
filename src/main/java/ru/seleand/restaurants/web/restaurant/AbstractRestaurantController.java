package ru.seleand.restaurants.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.seleand.restaurants.AuthorizedUser;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.service.RestaurantService;
import ru.seleand.restaurants.to.RestaurantWithVotes;

import java.util.List;

public abstract class AbstractRestaurantController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRestaurantController.class);

    @Autowired
    private RestaurantService service;

    public Restaurant create(Restaurant restaurant){
        restaurant.setId(null);
        LOG.info("Create restaurant {}", restaurant);
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

    public List<RestaurantWithVotes> getAll() {
        int userId = AuthorizedUser.id();
        LOG.info("Get all restaurants");
        return service.findAllWithVotes(userId);
    }

    public void update(Restaurant restaurant, int id) {
        restaurant.setId(id);
        LOG.info("Update restaursnt {}",restaurant);
        service.update(restaurant);
    }

}
