package ru.seleand.restaurants.web.restaurant;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.seleand.restaurants.model.Restaurant;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(AjaxRestaurantController.REST_URL)
public class AjaxRestaurantController extends AbstractRestaurantController{
    static final String REST_URL = "/ajax/restaurants";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name) {

        Restaurant restaurant = new Restaurant(id, name);
        if (restaurant.isNew()) {
            super.create(restaurant);
        } else {
            super.update(restaurant, id);
        }
    }


/*
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created = super.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
*/

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

/*
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        super.update(restaurant, id);
    }
*/

}