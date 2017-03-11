package ru.seleand.restaurants.repository;

import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.to.RestaurantWithVotes;

import java.util.List;

/**
 * Created by Asus on 02.11.2016.
 */
public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();

    default List<RestaurantWithVotes> findAllWithVotes(Integer userId){
        throw new UnsupportedOperationException();
    };

    default Restaurant getWithDishes(int id){
        throw new UnsupportedOperationException();
    }
}
