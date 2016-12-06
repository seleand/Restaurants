package ru.seleand.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.repository.RestaurantRepository;
import ru.seleand.restaurants.util.exception.ExceptionUtil;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Asus on 02.11.2016.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository repository;
    @Override
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must be not null");
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id),id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id),id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must be not null");
        repository.save(restaurant);
    }

    @Override
    public Restaurant getWithDishes(int id) {
        return ExceptionUtil.checkNotFoundWithId(repository.getWithDishes(id), id);
    }
}
