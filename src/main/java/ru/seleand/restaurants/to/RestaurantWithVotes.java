package ru.seleand.restaurants.to;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Asus on 26.02.2017.
 */
public class RestaurantWithVotes {
    @NotEmpty
    private String name;

    private Integer id;

    private Long votesQuantity;

    private boolean userVotedThisRestaurantToday;

    public RestaurantWithVotes() {
    }

    public RestaurantWithVotes(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public RestaurantWithVotes(String name, Integer id, Long votesQuantity, boolean userVotedThisRestaurantToday) {
        this.name = name;
        this.id = id;
        this.votesQuantity = votesQuantity;
        this.userVotedThisRestaurantToday = userVotedThisRestaurantToday;
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

    public Long getVotesQuantity() {
        return votesQuantity;
    }

    public void setVotesQuantity(Long votesQuantity) {
        this.votesQuantity = votesQuantity;
    }

    public boolean isUserVotedThisRestaurantToday() {
        return userVotedThisRestaurantToday;
    }

    public void setUserVotedThisRestaurantToday(boolean userVotedThisRestaurantToday) {
        this.userVotedThisRestaurantToday = userVotedThisRestaurantToday;
    }

    @Override
    public String toString() {
        return "RestaurantWithVotes{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", votesQuantity=" + votesQuantity +
                ", userVotedThisRestaurantToday=" + userVotedThisRestaurantToday +
                '}';
    }
}
