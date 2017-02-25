package ru.seleand.restaurants.web.dish;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.to.DishTo;
import ru.seleand.restaurants.util.DishUtil;

import java.util.List;

@RestController
@RequestMapping(DishAjaxController.REST_URL)
public class DishAjaxController extends AbstractDishController{
    static final String REST_URL = "/ajax/dishes";

    @GetMapping(value = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(@PathVariable("restaurantId") int restaurantId) {
        return super.getAll(restaurantId);
    }

    @GetMapping(value = "/{restaurantId}/dish/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        Dish dish = super.get(id, restaurantId);
        return dish;
    }

    @DeleteMapping(value = "/{restaurantId}/dish/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        super.delete(id, restaurantId);
    }

    @PostMapping
    public void updateOrCreate(DishTo dishTo) {
//        Dish dish = new Dish(id, date, description, price);
        if (dishTo.isNew()) {
            super.create(DishUtil.createNewFromTo(dishTo),dishTo.getRestaurantId());
        } else {
            super.update(dishTo);
        }
    }

/*
    @PutMapping(value = "/{restaurantId}/dish/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        super.update(dish, id, restaurantId);
    }
*/

}
