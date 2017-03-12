package ru.seleand.restaurants.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.to.RestaurantWithVotes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Override
    Restaurant findOne(Integer id);

    @Override
    List<Restaurant> findAll(Sort sort);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.dishes WHERE r.id = ?1")
    Restaurant getWithDishes(int id);

    @Query("SELECT new ru.seleand.restaurants.to.RestaurantWithVotes(r.name, r.id, COUNT(v1), " +
            "CASE COUNT(v2) WHEN 0 THEN false ELSE true END) " +
            "FROM Restaurant r LEFT JOIN FETCH Vote v1 ON r.id=v1.restaurant.id " +
            "LEFT JOIN FETCH Vote v2 ON (r.id=v2.restaurant.id AND v2.user.id=?1 AND v2.date=?2) " +
            "GROUP BY r.name, r.id")
    List<RestaurantWithVotes> findAllWithVotes(Integer userId, LocalDate date);
}
