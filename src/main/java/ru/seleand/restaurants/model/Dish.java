package ru.seleand.restaurants.model;

import java.time.LocalDate;

/**
 * Created by Asus on 01.11.2016.
 */
public class Dish extends BaseEntity{
    private final LocalDate date;

    private final String description;

    private final int price;

    public Dish(LocalDate date, String description, int price) {
        this.date = date;
        this.description = description;
        this.price = price;
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
