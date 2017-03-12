package ru.seleand.restaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.repository.RestaurantRepository;
import ru.seleand.restaurants.to.RestaurantWithVotes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository {
    private static final Sort SORT_NAME = new Sort("name");

/*
    @PersistenceContext
    private EntityManager em;
*/

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

    public List<RestaurantWithVotes> findAllWithVotes(Integer userId){
        LocalDate today = LocalDate.now();
//        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0,0));
        return crudRepository.findAllWithVotes(userId, today);
    }
}
