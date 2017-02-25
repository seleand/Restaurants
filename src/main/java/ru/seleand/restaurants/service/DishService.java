package ru.seleand.restaurants.service;

import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.to.DishTo;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Asus on 02.11.2016.
 */
public interface DishService {
    Dish save(Dish dish, int restaurantId);

    void delete(int id, int restaurantId) throws NotFoundException;

    Dish get(int id, int restaurantId) throws NotFoundException;

    List<Dish> getAll(int restaurantId);

    void update(Dish dish, int restaurantId);

    void update(DishTo dishTo);

    Dish getWithRestaurant(int id, int userId);

}
