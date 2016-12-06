package ru.seleand.restaurants.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.repository.RestaurantRepository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Asus on 12.11.2016.
 */

@Repository
public class JdbcRestaurantReposytoryImpl implements RestaurantRepository {

    private static final BeanPropertyRowMapper<Restaurant> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Restaurant.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertRestaurant;

    @Autowired
    public JdbcRestaurantReposytoryImpl(DataSource dataSource) {
        this.insertRestaurant = new SimpleJdbcInsert(dataSource)
                .withTableName("restaurants")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", restaurant.getId())
                .addValue("name", restaurant.getName());

        if (restaurant.isNew()) {
            Number newKey = insertRestaurant.executeAndReturnKey(map);
            restaurant.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE restaurants SET name=:name WHERE id=:id", map);
        }
        return restaurant;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM restaurants WHERE id=?", id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        List<Restaurant> restaurants = jdbcTemplate.query("SELECT * FROM restaurants WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(restaurants);
    }

    @Override
    public List<Restaurant> getAll() {
        return jdbcTemplate.query("SELECT * FROM restaurants ORDER BY name", ROW_MAPPER);
    }

}
