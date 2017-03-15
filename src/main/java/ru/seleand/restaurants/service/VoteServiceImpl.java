package ru.seleand.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.seleand.restaurants.model.Vote;
import ru.seleand.restaurants.repository.VoteRepository;
import ru.seleand.restaurants.util.exception.ExceptionUtil;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Asus on 04.03.2017.
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRepository repository;

    @Override
    public Vote save(Vote vote, int restaurantId, int userId) {
        Assert.notNull(vote, "vote must be not null");
        return repository.save(vote, restaurantId, userId);
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
    public void update(Vote vote, int restaurantId, int userId) {
        Assert.notNull(vote, "vote must be not null");
        repository.save(vote, restaurantId, userId);
    }

    @Override
    public void changeVoteState(int restaurantId, int userId) {
        LocalDate today = LocalDate.now();
        List<Vote> votesByUserToday = repository.getUserVotesBetween(today, today, userId);
        if (votesByUserToday.size()==0){
            Vote vote = new Vote(today);
//            LOG.info("New vote by restaurant with id {} for User {}", restaurantId, userId);
            repository.save(vote, restaurantId, userId);
        }
        else {
            LocalTime timeNow = LocalTime.now();
            LocalTime time11 = LocalTime.of(11,0);
            if (timeNow.isAfter(time11)) {

            }
            else {
                Vote vote = votesByUserToday.get(0);
                if (restaurantId==vote.getRestaurant().getId()){
//                    LOG.info("delete vote by restaurant with id {} for User {}", restaurantId, userId);
                    repository.delete(vote.getId(), userId);
                }
                else {
//                    LOG.info("update vote by restaurant with id {} for User {}", restaurantId, userId);
                    repository.save(vote, restaurantId, userId);
                }
            }
        }

    }

    @Override
    public List<Vote> getUserVotes(int userId) {
        return repository.getUserVotes(userId);
    }

    @Override
    public List<Vote> getUserVotesBetween(LocalDate startDate, LocalDate endDate, int userId) {
        Assert.notNull(startDate, "startDate must be not null");
        Assert.notNull(endDate, "endDate must be not null");
        return repository.getUserVotesBetween(startDate, endDate, userId);
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Vote> getVotesBetween(LocalDate startDate, LocalDate endDate) {
        Assert.notNull(startDate, "startDate must be not null");
        Assert.notNull(endDate, "endDate must be not null");
        return repository.getVotesBetween(startDate, endDate);
    }
}
