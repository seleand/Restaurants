package ru.seleand.restaurants.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Asus on 01.11.2016.
 */
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity{

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant() {
        super();
    }
}
