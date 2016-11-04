package ru.seleand.restaurants.model;

import java.time.LocalDate;

/**
 * Created by Asus on 01.11.2016.
 */
public class Dish extends BaseEntity{
    private LocalDate date;

    private String description;

    private int price;

    private final int restaurantId;

    public Dish(LocalDate date, String description, int price, int restaurantId) {
        this.date = date;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public Dish(Integer id, LocalDate date, String description, Integer price, int restaurantId) {
        super(id);
        this.date = date;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public Dish(LocalDate date, int restaurantId) {
        super();
        this.date = date;
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
