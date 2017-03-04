package ru.seleand.restaurants.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Asus on 02.03.2017.
 */
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(User user, LocalDateTime dateTime, Restaurant restaurant) {
        this.user = user;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, User user, LocalDateTime dateTime, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "user=" + user +
                ", dateTime=" + dateTime +
                ", restaurant=" + restaurant +
                '}';
    }
}
