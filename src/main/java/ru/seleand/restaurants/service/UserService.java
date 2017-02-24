package ru.seleand.restaurants.service;


import ru.seleand.restaurants.model.User;
import ru.seleand.restaurants.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void update(User user);

    void enable(int id, boolean enable);

    void evictCache();
}
