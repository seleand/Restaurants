package ru.seleand.restaurants.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.seleand.restaurants.AuthorizedUser;
import ru.seleand.restaurants.model.Vote;
import ru.seleand.restaurants.service.VoteService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Asus on 12.03.2017.
 */

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {
    static final String REST_URL = "/rest/votes/restaurant";
    private static final Logger LOG = LoggerFactory.getLogger(VoteRestController.class);

    @Autowired
    private VoteService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote get(@PathVariable("id") int id) {
        int userId = AuthorizedUser.id();
        LOG.info("Get vote by id {} for User {}",id, userId);
        return service.get(id, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(int id) {
        int userId = AuthorizedUser.id();
        LOG.info("delete meal {} for User {}", id, userId);
        service.delete(id, userId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(Vote vote, int id) {
        vote.setId(id);
        int userId = AuthorizedUser.id();
        LOG.info("update {} for User {}", vote, userId);
        service.update(vote, userId);
    }

    public Vote create(Vote vote) {
        vote.setId(null);
        int userId = AuthorizedUser.id();
        LOG.info("create {} for User {}", vote, userId);
        return service.save(vote, userId);
    }

/*
    public List<Vote> getByUserBetween(LocalDate startDate, LocalDate endDate) {
        int userId = AuthorizedUser.id();
        LOG.info("getBetween dates {} - {} for User {}", startDate, endDate, userId);
        return service.getUserVotesBetween(startDate, endDate, userId);
    }
*/

}
