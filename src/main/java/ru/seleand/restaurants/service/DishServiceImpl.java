package ru.seleand.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.repository.DishRepository;
import ru.seleand.restaurants.util.exception.ExceptionUtil;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Asus on 02.11.2016.
 */
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishRepository repository;
    @Override
    public Dish save(Dish dish, int restaurantId) {
        return repository.save(dish, restaurantId);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, restaurantId),id);
    }

    @Override
    public Dish get(int id, int restaurantId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, restaurantId),id);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Override
    public void update(Dish dish, int restaurantId) {
        repository.save(dish, restaurantId);
    }
}