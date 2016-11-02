package ru.seleand.restaurants.service;

import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Asus on 02.11.2016.
 */
public interface DishService {
    Dish save(Dish dish);

    void delete(int id) throws NotFoundException;

    Dish get(int id) throws NotFoundException;

    List<Dish> getAll();

    void update(Dish dish);

}
