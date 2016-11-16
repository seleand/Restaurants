package ru.seleand.restaurants.repository.mock;

import org.springframework.stereotype.Repository;
import ru.seleand.restaurants.model.User;
import ru.seleand.restaurants.repository.UserRepository;

import java.util.List;

/**
 * Created by Asus on 16.11.2016.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
