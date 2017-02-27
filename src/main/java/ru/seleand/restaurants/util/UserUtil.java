package ru.seleand.restaurants.util;

import ru.seleand.restaurants.model.Role;
import ru.seleand.restaurants.model.User;
import ru.seleand.restaurants.to.UserTo;

public class UserUtil {

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }
}