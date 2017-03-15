package ru.seleand.restaurants.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.model.User;
import ru.seleand.restaurants.model.Vote;
import ru.seleand.restaurants.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Asus on 04.03.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaVoteRepositoryImpl implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote, int restaurantId, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(em.getReference(User.class, userId));
        vote.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Vote.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId).executeUpdate() != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        List<Vote> votes = em.createNamedQuery(Vote.GET)
                .setParameter("id", id)
                .setParameter("userId", userId).getResultList();
        return DataAccessUtils.singleResult(votes);
    }

    @Override
    public List<Vote> getUserVotes(int userId) {
        return em.createNamedQuery(Vote.USER_VOTES, Vote.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Vote> getUserVotesBetween(LocalDate startDate, LocalDate endDate, int userId) {
        return em.createNamedQuery(Vote.USER_VOTES_BETWEEN, Vote.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
    }

    @Override
    public List<Vote> getAll() {
        return em.createNamedQuery(Vote.GET_ALL, Vote.class).getResultList();
    }

    @Override
    public List<Vote> getVotesBetween(LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Vote.VOTES_BETWEEN, Vote.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
    }
}
