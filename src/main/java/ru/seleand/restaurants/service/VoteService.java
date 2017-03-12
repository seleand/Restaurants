package ru.seleand.restaurants.service;

import ru.seleand.restaurants.model.Vote;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Asus on 04.03.2017.
 */
public interface VoteService {

    Vote save(Vote vote, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Vote get(int id, int userId) throws NotFoundException;

    void update(Vote vote, int userId);

    List<Vote> getUserVotes(int userId);

    List<Vote> getUserVotesBetween(LocalDate startDate, LocalDate endDate, int userId);

    List<Vote> getAll();

    List<Vote> getVotesBetween(LocalDate startDate, LocalDate endDate);

}
