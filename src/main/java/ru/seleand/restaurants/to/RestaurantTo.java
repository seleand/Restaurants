package ru.seleand.restaurants.to;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Asus on 26.02.2017.
 */
public class RestaurantTo {
    @NotEmpty
    private String name;

    private Integer id;

    public RestaurantTo() {
    }

    public RestaurantTo(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
