package ru.seleand.restaurants.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Asus on 02.03.2017.
 */

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId"),
        @NamedQuery(name = Vote.GET, query = "SELECT v FROM Vote v WHERE v.id=:id AND v.user.id=:userId"),
        @NamedQuery(name = Vote.USER_VOTES, query = "SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.dateTime desc"),
        @NamedQuery(name = Vote.USER_VOTES_BETWEEN, query = "SELECT v FROM Vote v " +
                "WHERE v.user.id=:userId AND v.dateTime BETWEEN :startDate AND :endDate ORDER BY v.dateTime DESC"),
        @NamedQuery(name = Vote.GET_ALL, query = "SELECT v FROM Vote v ORDER BY v.dateTime desc"),
        @NamedQuery(name = Vote.VOTES_BETWEEN, query = "SELECT v FROM Vote v " +
                "WHERE v.dateTime BETWEEN :startDate AND :endDate ORDER BY v.dateTime DESC")
})

@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

    public static final String DELETE = "Vote.delete";
    public static final String GET = "Vote.get";
    public static final String USER_VOTES = "Vote.getUserVotes";
    public static final String USER_VOTES_BETWEEN = "Vote.getUserVotesBetween";
    public static final String GET_ALL = "Vote.getAllVotes";
    public static final String VOTES_BETWEEN = "Vote.getVotesBetween";

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
