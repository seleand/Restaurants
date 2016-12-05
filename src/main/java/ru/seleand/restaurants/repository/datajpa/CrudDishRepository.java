package ru.seleand.restaurants.repository.datajpa;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.seleand.restaurants.model.Dish;

import javax.persistence.Access;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Override
    @Transactional
    Dish save(Dish dish);

    @Query("SELECT d FROM Dish d WHERE d.id=:id and d.restaurant.id=:restaurantId")
    List<Dish> get(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Override
    Dish findOne(Integer id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId ORDER BY d.date desc, d.description")
    List<Dish> findAll(@Param("restaurantId") int restaurantId);
}
