package ru.seleand.restaurants.repository;

import ru.seleand.restaurants.model.Dish;

import java.util.List;

/**
 * Created by Asus on 02.11.2016.
 */
public interface  DishRepository {
    Dish save(Dish dish, int restaurantId);

    // false if not found
    boolean delete(int id, int restaurantId);

    // null if not found
    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);


    default Dish getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }

}
