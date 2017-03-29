package ru.seleand.restaurants.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.seleand.restaurants.RestaurantTestData.*;

/**
 * Created by Asus on 29.03.2017.
 */
public abstract class AbstractRestaurantServiceTest extends AbstractServiceTest{
    @Autowired
    protected RestaurantService service;

    @Test
    public void testSave() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "New");
        Restaurant created = service.save(newRestaurant);
        newRestaurant.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(newRestaurant, RESTAURANT_1, RESTAURANT_2), service.getAll());
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(RESTAURANT_ID+1);
        MATCHER.assertCollectionEquals(Collections.singletonList(RESTAURANT_1), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT_ID);
        MATCHER.assertEquals(RESTAURANT_1, restaurant);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Restaurant> all = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT_1, RESTAURANT_2), all);
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_1.getId(),RESTAURANT_1.getName());
        updated.setName("UpdatedName");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(RESTAURANT_ID));
    }

}
