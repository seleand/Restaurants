package ru.seleand.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.repository.DishRepository;
import ru.seleand.restaurants.to.DishTo;
import ru.seleand.restaurants.util.DishUtil;
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
        Assert.notNull(dish, "dish must be not null");
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
        Assert.notNull(dish, "dish must be not null");
        repository.save(dish, restaurantId);
    }

    @Transactional
    @Override
    public void update(DishTo dishTo) {
        Assert.notNull(dishTo, "dishTo must be not null");
        Dish dish = repository.get(dishTo.getId(), dishTo.getRestaurantId());
        repository.save(DishUtil.updateFromTo(dish,dishTo), dishTo.getRestaurantId());
    }

    @Override
    public Dish getWithRestaurant(int id, int restaurantId) {
        return ExceptionUtil.checkNotFoundWithId(repository.getWithRestaurant(id, restaurantId), id);
    }

}
