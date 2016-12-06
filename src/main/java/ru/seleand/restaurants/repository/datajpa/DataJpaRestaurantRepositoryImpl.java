package ru.seleand.restaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository {
    private static final Sort SORT_NAME = new Sort("name");

    @Autowired
    private CrudRestaurantRepository crudRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRepository.findOne(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }


    @Override
    public Restaurant getWithDishes(int id) {
        return crudRepository.getWithDishes(id);
    }

}
