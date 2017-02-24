package ru.seleand.restaurants.web.dish;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.seleand.restaurants.model.Dish;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(DishAjaxController.REST_URL)
public class DishAjaxController extends AbstractDishController{
    static final String REST_URL = "/ajax/dishes";

    @GetMapping(value = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(@PathVariable("restaurantId") int restaurantId) {
        return super.getAll(restaurantId);
    }


    @DeleteMapping(value = "/{restaurantId}/dish/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        super.delete(id, restaurantId);
    }

    @PostMapping
    public void updateOrCreate(@RequestParam("id") Integer id,
                               @RequestParam("restaurantId") Integer restaurantId,
                               @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               @RequestParam("description") String description,
                               @RequestParam("price") int price) {
        Dish dish = new Dish(id, date, description, price);
        if (dish.isNew()) {
            super.create(dish,restaurantId);
        } else {
            super.update(dish, id, restaurantId);
        }
    }

/*
    @PutMapping(value = "/{restaurantId}/dish/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        super.update(dish, id, restaurantId);
    }
*/

}
