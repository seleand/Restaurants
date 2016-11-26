package ru.seleand.restaurants.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
})

@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity{

    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_SORTED = "Restaurant.getAllSorted";

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant() {
        super();
    }
}
