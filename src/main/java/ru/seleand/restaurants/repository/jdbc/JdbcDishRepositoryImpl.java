package ru.seleand.restaurants.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.repository.DishRepository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Asus on 12.11.2016.
 */

@Repository
public class JdbcDishRepositoryImpl implements DishRepository {

    private static final BeanPropertyRowMapper<Dish> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Dish.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertDish;

    @Autowired
    public JdbcDishRepositoryImpl(DataSource dataSource) {
        this.insertDish = new SimpleJdbcInsert(dataSource)
                .withTableName("dishes")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Dish save(Dish dish, int restaurantId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", dish.getId())
                .addValue("description", dish.getDescription())
                .addValue("date",dish.getDate())
                .addValue("price",dish.getPrice())
                .addValue("restaurant_id",restaurantId);

        if (dish.isNew()) {
            Number newKey = insertDish.executeAndReturnKey(map);
            dish.setId(newKey.intValue());
        } else {
            if (namedParameterJdbcTemplate.update(
                    "UPDATE dishes SET description=:description, date=:date, price=:price " +
                            "WHERE id=:id AND restaurant_id=:restaurant_id", map)==0) {
                return null;
            }
        }
        return dish;
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return jdbcTemplate.update("DELETE FROM dishes WHERE id=? AND restaurant_id=?", id, restaurantId) != 0;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        List<Dish> dishes = jdbcTemplate.query("SELECT * FROM dishes WHERE id=? AND restaurant_id=?", ROW_MAPPER, id, restaurantId);
        return DataAccessUtils.singleResult(dishes);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return jdbcTemplate.query("SELECT * FROM dishes WHERE restaurant_id=? ORDER BY date DESC, description  ", ROW_MAPPER, restaurantId);
    }

    @Override
    public void init() {

    }
}
