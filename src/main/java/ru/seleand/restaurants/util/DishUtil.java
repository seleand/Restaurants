package ru.seleand.restaurants.util;


import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.to.DishTo;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Asus on 01.11.2016.
 */
public class DishUtil {

  public static Dish createNewFromTo(DishTo dishTo){
/*
      Date date = dishTo.getDate();
      LocalDate localDate = LocalDate.ofEpochDay(date.getTime());
*/
      return new Dish(null, dishTo.getDate(), dishTo.getDescription(), getIntPrice(dishTo));
  }


    public static Dish updateFromTo(Dish dish, DishTo dishTo) {
/*
        Date date = dishTo.getDate();
        LocalDate localDate = LocalDate.ofEpochDay(date.getTime());
*/
        dish.setDate(dishTo.getDate());
        dish.setDescription(dishTo.getDescription());
        dish.setPrice(getIntPrice(dishTo));
        return dish;
    }

    private static Integer getIntPrice(DishTo dishTo){
        String stringPrice = dishTo.getPrice();
        Double doublePrice = 0.;
        if (!stringPrice.isEmpty()) {
            doublePrice = Double.parseDouble(dishTo.getPrice()) * 100;
        }
        return doublePrice.intValue();
    }
/*
    public static final List<Dish> DISHES = Arrays.asList(
            new Dish(LocalDate.of(2015, Month.MAY, 30), "Блюдо1", 5015),
            new Dish(LocalDate.of(2015, Month.MAY, 30), "Блюдо2", 6000),
            new Dish(LocalDate.of(2015, Month.MAY, 30), "Блюдо3", 5000),
            new Dish(LocalDate.of(2015, Month.MAY, 29), "Блюдо4", 35099),
            new Dish(LocalDate.of(2015, Month.MAY, 29), "Блюдо5", 55000),
            new Dish(LocalDate.of(2015, Month.MAY, 29), "Блюдо6", 7000));
*/

}
