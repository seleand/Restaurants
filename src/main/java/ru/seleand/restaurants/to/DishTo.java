package ru.seleand.restaurants.to;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Asus on 24.02.2017.
 */
public class DishTo {
    private Integer restaurantId;

    private Integer id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate date;
//    private Date date;

    @NotEmpty
    private String description;

    private String price;

    public DishTo() {
    }

    public DishTo(Integer restaurantId, Integer id, LocalDate date, String description, String price) {
        this.restaurantId = restaurantId;
        this.id = id;
        this.date = date;
        this.description = description;
        this.price = price;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "restaurantId=" + restaurantId +
                ", id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
