package ru.seleand.restaurants;

import ru.seleand.restaurants.matcher.ModelMatcher;
import ru.seleand.restaurants.model.Restaurant;

import static ru.seleand.restaurants.model.BaseEntity.START_SEQ;

/**
 * Created by Asus on 15.12.2016.
 */
public class RestaurantTestData {
    public static final ModelMatcher<Restaurant> MATCHER = ModelMatcher.of(Restaurant.class);

    public static final int RESTAURANT_ID = START_SEQ+2;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID, "Рога и копыта");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID+1, "Розовые пятачки");

}
