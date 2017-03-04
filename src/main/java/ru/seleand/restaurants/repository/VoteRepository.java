package ru.seleand.restaurants.repository;

import ru.seleand.restaurants.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Asus on 02.03.2017.
 */
public interface VoteRepository {
    Vote save(Vote vote, int userId);

    boolean delete(int id, int userId);

    Vote get(int id, int userId);

    List<Vote> getUserVotes(int userId);

    List<Vote> getUserVotesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    List<Vote> getAll();

    List<Vote> getVotesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
