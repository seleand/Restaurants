package ru.seleand.restaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.seleand.restaurants.model.User;
import ru.seleand.restaurants.repository.UserRepository;

import java.util.List;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {

    @Autowired
    private CrudUserRepository crudRepository;

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll();
    }
}
