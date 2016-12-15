package ru.seleand.restaurants.web.json;

import org.junit.Test;
import ru.seleand.restaurants.DishTestData;
import ru.seleand.restaurants.model.Dish;

import java.util.List;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(DishTestData.DISH_1);
        System.out.println(json);
        Dish dish = JsonUtil.readValue(json, Dish.class);
        DishTestData.MATCHER.assertEquals(DishTestData.DISH_1, dish);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(DishTestData.DISHES_RESTAURANT_1);
        System.out.println(json);
        List<Dish> dishes = JsonUtil.readValues(json, Dish.class);
        DishTestData.MATCHER.assertCollectionEquals(DishTestData.DISHES_RESTAURANT_1, dishes);
    }
}