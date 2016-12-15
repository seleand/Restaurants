package ru.seleand.restaurants.model;


import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
})

@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity{

    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_SORTED = "Restaurant.getAllSorted";

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC, description")
//    @JsonIgnore
    protected List<Dish> dishes;


    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant() {
        super();
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
