package ru.seleand.restaurants;

import ru.seleand.restaurants.matcher.ModelMatcher;
import ru.seleand.restaurants.model.Dish;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static ru.seleand.restaurants.model.BaseEntity.START_SEQ;

/**
 * Created by Asus on 15.12.2016.
 */
public class DishTestData {
    public static final ModelMatcher<Dish> MATCHER = new ModelMatcher<>();

    public static final int DISH_ID = START_SEQ+4;

    public static final Dish DISH_1 = new Dish(DISH_ID, LocalDate.of(2016,11,13),"dish 1 rest1",5000);
    public static final Dish DISH_2 = new Dish(DISH_ID+1, LocalDate.of(2016,11,13),"dish 2 rest1",6515);
    public static final Dish DISH_3 = new Dish(DISH_ID+2, LocalDate.of(2016,11,12),"dish 3 rest1",8005);
    public static final Dish DISH_4 = new Dish(DISH_ID+3, LocalDate.of(2016,11,13),"dish 1 rest2",7000);
    public static final Dish DISH_5 = new Dish(DISH_ID+4, LocalDate.of(2016,11,13),"dish 2 rest2",5700);
    public static final Dish DISH_6 = new Dish(DISH_ID+5, LocalDate.of(2016,11,12),"dish 3 rest2",6000);

    public static final List<Dish> DISHES_RESTAURANT_1 = Arrays.asList(DISH_1, DISH_2, DISH_3);
    public static final List<Dish> DISHES_RESTAURANT_2 = Arrays.asList(DISH_4, DISH_5, DISH_6);

}
