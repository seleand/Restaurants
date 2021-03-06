package ru.seleand.restaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import ru.seleand.restaurants.model.Vote;
import ru.seleand.restaurants.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Asus on 02.03.2017.
 */
@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Vote save(Vote vote, int restaurantId, int userId) {
        if (!vote.isNew()&&get(vote.getId(),userId)==null) {
            return null;
        }
        vote.setUser(crudUserRepository.findOne(userId));
        vote.setRestaurant(crudRestaurantRepository.findOne(restaurantId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId)!=0;
    }

    @Override
    public Vote get(int id, int userId) {
        List<Vote> votes = crudVoteRepository.get(id, userId);
        return DataAccessUtils.singleResult(votes);
    }

    @Override
    public List<Vote> getUserVotes(int userId) {
        return crudVoteRepository.findUserVotes(userId);
    }

    @Override
    public List<Vote> getUserVotesBetween(LocalDate startDate, LocalDate endDate, int userId) {
        return crudVoteRepository.findUserVotesBetween(startDate, endDate, userId);
    }

    @Override
    public List<Vote> getAll() {
        return crudVoteRepository.findAll();
    }

    @Override
    public List<Vote> getVotesBetween(LocalDate startDate, LocalDate endDate) {
        return crudVoteRepository.findVotesBetween(startDate, endDate);
    }
}
