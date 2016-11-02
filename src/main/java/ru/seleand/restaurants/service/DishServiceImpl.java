package ru.seleand.restaurants.service;

import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.repository.DishRepository;
import ru.seleand.restaurants.util.exception.ExceptionUtil;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Asus on 02.11.2016.
 */
public class DishServiceImpl implements DishService {
    private DishRepository repository;
    @Override
    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id),id);
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id),id);
    }

    @Override
    public List<Dish> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Dish dish) {
        repository.save(dish);
    }
}
