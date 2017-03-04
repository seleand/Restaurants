package ru.seleand.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.seleand.restaurants.model.Vote;
import ru.seleand.restaurants.repository.VoteRepository;
import ru.seleand.restaurants.util.exception.ExceptionUtil;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Asus on 04.03.2017.
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRepository repository;

    @Override
    public Vote save(Vote vote, int userId) {
        Assert.notNull(vote, "vote must be not null");
        return repository.save(vote, userId);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Vote get(int id, int userId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void update(Vote vote, int userId) {
        Assert.notNull(vote, "vote must be not null");
        repository.save(vote, userId);
    }

    @Override
    public List<Vote> getUserVotes(int userId) {
        return repository.getUserVotes(userId);
    }

    @Override
    public List<Vote> getUserVotesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Assert.notNull(startDateTime, "startDateTime must be not null");
        Assert.notNull(endDateTime, "endDateTime must be not null");
        return repository.getUserVotesBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Vote> getVotesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Assert.notNull(startDateTime, "startDateTime must be not null");
        Assert.notNull(endDateTime, "endDateTime must be not null");
        return repository.getVotesBetween(startDateTime, endDateTime);
    }
}
