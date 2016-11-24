package ru.seleand.restaurants.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.repository.DishRepository;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaDishRepositoryImpl implements DishRepository {
    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int restaurantId) {
        return false;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return null;
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return null;
    }

    @Override
    public void init() {

    }
}
