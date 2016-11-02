package ru.seleand.restaurants.repository;

import ru.seleand.restaurants.model.Dish;

import java.util.List;

/**
 * Created by Asus on 02.11.2016.
 */
public interface DishRepository {
    Dish save(Dish dish);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    List<Dish> getAll();


}
