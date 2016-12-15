package ru.seleand.restaurants.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId"),
        @NamedQuery(name = Dish.GET, query = "SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId"),
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId ORDER BY d.date desc, d.description"),
})

@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity{

    public static final String DELETE = "Dish.delete";
    public static final String GET = "Dish.get";
    public static final String ALL_SORTED = "Dish.getAllSorted";

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "description", nullable = false)
    @NotEmpty
    private String description;

    @Column(name = "price", nullable = false)
    @Digits(fraction = 2, integer = 10)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
//    @JsonIgnore
    private Restaurant restaurant;
/*
    private int restaurantId;
*/

    public Dish() {
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Dish(LocalDate date, String description, int price) {
        this.date = date;
        this.description = description;
        this.price = price;
    }

    public Dish(Integer id, LocalDate date, String description, Integer price) {
        super(id);
        this.date = date;
        this.description = description;
        this.price = price;
    }

    public Dish(LocalDate date) {
        super();
        this.date = date;
    }

    public Dish(LocalDate date, String description, int price, Restaurant restaurant) {
        this.date = date;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Dish(Integer id, LocalDate date, String description, Integer price, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Dish(LocalDate date, Restaurant restaurant) {
        super();
        this.date = date;
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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
