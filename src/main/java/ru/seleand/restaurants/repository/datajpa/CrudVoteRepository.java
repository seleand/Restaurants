package ru.seleand.restaurants.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.seleand.restaurants.model.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Asus on 02.03.2017.
 */
@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    List<Vote> findAll();

    @Override
    @Transactional
    Vote save(Vote vote);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.id=:id and v.user.id=:userId")
    List<Vote> get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.date desc")
    List<Vote> findUserVotes(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.date BETWEEN :startDate AND :endDate ORDER BY v.date desc")
    List<Vote> findUserVotesBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.date BETWEEN :startDate AND :endDate ORDER BY v.date desc")
    List<Vote> findVotesBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
