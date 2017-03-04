package ru.seleand.restaurants.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.seleand.restaurants.model.Vote;
import ru.seleand.restaurants.repository.VoteRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Asus on 04.03.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcVoteRepositoryImpl implements VoteRepository {

    private static final BeanPropertyRowMapper<Vote> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Vote.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertVote;

    @Autowired
    public JdbcVoteRepositoryImpl(DataSource dataSource) {
        this.insertVote = new SimpleJdbcInsert(dataSource)
                .withTableName("votes")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", vote.getId())
                .addValue("date_time",vote.getDateTime())
                .addValue("user_id",userId)
                .addValue("restaurant_id",vote.getRestaurant().getId());

        if (vote.isNew()) {
            Number newKey = insertVote.executeAndReturnKey(map);
            vote.setId(newKey.intValue());
        } else {
            if (namedParameterJdbcTemplate.update(
                    "UPDATE votes SET date_time=:date_time, restaurant_id=:restaurant_id " +
                            "WHERE id=:id AND user_id=:user_id", map)==0) {
                return null;
            }
        }
        return vote;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM votes WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        List<Vote> votes = jdbcTemplate.query("SELECT * FROM votes WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(votes);
    }

    @Override
    public List<Vote> getUserVotes(int userId) {
        return jdbcTemplate.query("SELECT * FROM votes WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<Vote> getUserVotesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return jdbcTemplate.query("SELECT * FROM votes WHERE user_id=? AND date_time BETWEEN ? AND ? ORDER BY date_time DESC",
//                ROW_MAPPER, userId, toDbDateTime(startDateTime), toDbDateTime(endDateTime));
                ROW_MAPPER, userId, startDateTime, endDateTime);
    }

    @Override
    public List<Vote> getAll() {
        return jdbcTemplate.query("SELECT * FROM votes ORDER BY date_time DESC", ROW_MAPPER);
    }

    @Override
    public List<Vote> getVotesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return jdbcTemplate.query("SELECT * FROM votes WHERE date_time BETWEEN ? AND ? ORDER BY date_time DESC",
//                ROW_MAPPER, userId, toDbDateTime(startDateTime), toDbDateTime(endDateTime));
                ROW_MAPPER, startDateTime, endDateTime);
    }
}
