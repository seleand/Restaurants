package ru.seleand.restaurants.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static ru.seleand.restaurants.DishTestData.*;
import static ru.seleand.restaurants.RestaurantTestData.RESTAURANT_ID;

/**
 * Created by Asus on 29.03.2017.
 */
public class AbstractDishServiceTest extends AbstractServiceTest {
    @Autowired
    protected DishService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(DISH_ID, RESTAURANT_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_2, DISH_3), service.getAll(RESTAURANT_ID));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(DISH_ID, 1);
    }

    @Test
    public void testSave() throws Exception {
        Dish created = new Dish(null, LocalDate.now(),"new dish",5000);
        service.save(created, RESTAURANT_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, DISH_2, DISH_1, DISH_3), service.getAll(RESTAURANT_ID));
    }

    @Test
    public void testGet() throws Exception {
        Dish actual = service.get(DISH_ID, RESTAURANT_ID);
        MATCHER.assertEquals(DISH_1, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(DISH_ID, RESTAURANT_ID+1);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = new Dish(DISH_ID, LocalDate.of(2016,11,13),"Рога под острым соусом обновленные",5000);
        service.update(updated, RESTAURANT_ID);
        MATCHER.assertEquals(updated, service.get(DISH_ID, RESTAURANT_ID));
    }

    @Test
    public void testNotFoundUpdate() throws Exception {
        Dish item = service.get(DISH_ID, RESTAURANT_ID);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + DISH_ID);
        service.update(item, RESTAURANT_ID+1);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(DISHES_RESTAURANT_1, service.getAll(RESTAURANT_ID));
    }

/*
    @Test
    public void testGetBetween() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1),
                service.getBetweenDates(LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 30), USER_ID));
    }
*/

}
