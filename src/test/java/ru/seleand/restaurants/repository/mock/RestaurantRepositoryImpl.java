package ru.seleand.restaurants.repository.mock;

import org.springframework.stereotype.Repository;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.repository.RestaurantRepository;

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
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private Map<Integer, Restaurant> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private static final Comparator<Restaurant> RESTAURANT_COMPARATOR = Comparator.comparing(Restaurant::getName);
    @Override
    public Restaurant save(Restaurant restaurant) {
        Objects.requireNonNull(restaurant);
        if (restaurant.isNew()){
            restaurant.setId(counter.incrementAndGet());
        }
        repository.put(restaurant.getId(),restaurant);
        return restaurant;
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id)!=null;
    }

    @Override
    public Restaurant get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.values()
                .stream()
                .sorted(RESTAURANT_COMPARATOR)
                .collect(Collectors.toList());
    }

    public void init() {
        save(new Restaurant(1,"restaurant 1"));
        save(new Restaurant(2,"restaurant 2"));
    }
}
