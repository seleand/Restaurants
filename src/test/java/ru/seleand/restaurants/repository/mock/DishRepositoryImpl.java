package ru.seleand.restaurants.repository.mock;

import org.springframework.stereotype.Repository;
import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.repository.DishRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Asus on 03.11.2016.
 */
@Repository
public class DishRepositoryImpl implements DishRepository {
    private Map<Integer, Map<Integer,Dish>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private static final Comparator<Dish> DISH_COMPARATOR = Comparator.comparing(Dish::getDate).reversed().thenComparing(Dish::getDescription);

    @Override
    public Dish save(Dish dish, int restaurantId) {
        Objects.requireNonNull(dish);
        if (dish.isNew()){
            dish.setId(counter.incrementAndGet());
        }
        Map<Integer,Dish> dishMap = repository.computeIfAbsent(restaurantId,ConcurrentHashMap::new);
        dishMap.put(dish.getId(),dish);
        return dish;
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        Map<Integer,Dish> dishMap = repository.get(restaurantId);
        if (dishMap==null) return false;
        return dishMap.remove(id)!=null;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        Map<Integer,Dish> dishMap = repository.get(restaurantId);
        if (dishMap==null) return null;
        return dishMap.get(id);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        Map<Integer,Dish> dishMap = repository.computeIfAbsent(restaurantId,ConcurrentHashMap::new);
        return dishMap.values().stream().sorted(DISH_COMPARATOR).collect(Collectors.toList());
    }

    @Override
    public void init() {
        save(new Dish(LocalDate.of(2015, Month.MAY, 30), "Блюдо1", 5015, 1), 1);
        save(new Dish(LocalDate.of(2015, Month.MAY, 30), "Блюдо2", 5000, 1), 1);
        save(new Dish(LocalDate.of(2015, Month.MAY, 30), "Блюдо3", 50015, 1), 1);
        save(new Dish(LocalDate.of(2015, Month.MAY, 30), "Блюдо4", 7000, 2), 2);
        save(new Dish(LocalDate.of(2015, Month.MAY, 30), "Блюдо5", 6005, 2), 2);
        save(new Dish(LocalDate.of(2015, Month.MAY, 30), "Блюдо6", 5015, 2), 2);
    }
}
