package ru.seleand.restaurants.service;

import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.to.RestaurantWithVotes;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Asus on 02.11.2016.
 */
public interface RestaurantService {
    Restaurant save(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    List<Restaurant> getAll();

    void update(Restaurant restaurant);

    List<RestaurantWithVotes> findAllWithVotes(Integer userId);

    Restaurant getWithDishes(int id);

}
