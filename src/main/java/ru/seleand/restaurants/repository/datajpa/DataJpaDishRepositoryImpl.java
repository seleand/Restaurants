package ru.seleand.restaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.repository.DishRepository;
import ru.seleand.restaurants.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaDishRepositoryImpl implements DishRepository{

    @Autowired
    private CrudDishRepository crudRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew()&&get(dish.getId(), restaurantId)==null){
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.findOne(restaurantId));
        return crudRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudRepository.delete(id, restaurantId)!=0;
    }

    @Override
    public Dish get(int id, int restaurantId) {
//        Dish dish = crudRepository.findOne(id);
        List<Dish> dishes  = crudRepository.get(id, restaurantId);
        return DataAccessUtils.singleResult(dishes);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return crudRepository.findAll(restaurantId);
    }


    @Override
    public Dish getWithRestaurant(int id, int userId) {
        return crudRepository.getWithRestaurant(id, userId);
    }

}
