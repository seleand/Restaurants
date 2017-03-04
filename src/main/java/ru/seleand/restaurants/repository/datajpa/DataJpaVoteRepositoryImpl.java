package ru.seleand.restaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import ru.seleand.restaurants.model.Vote;
import ru.seleand.restaurants.repository.VoteRepository;

import java.time.LocalDateTime;
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

    @Override
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew()&&get(vote.getId(),userId)==null) {
            return null;
        }
        vote.setUser(crudUserRepository.findOne(userId));
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
    public List<Vote> getUserVotesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudVoteRepository.findUserVotesBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public List<Vote> getAll() {
        return crudVoteRepository.findAll();
    }

    @Override
    public List<Vote> getVotesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return crudVoteRepository.findVotesBetween(startDateTime, endDateTime);
    }
}
