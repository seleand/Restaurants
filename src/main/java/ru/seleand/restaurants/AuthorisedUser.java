package ru.seleand.restaurants;

import ru.seleand.restaurants.model.Role;

/**
 * Created by Asus on 10.11.2016.
 */
public class AuthorisedUser {

    private static int id = 1;

    private static Role mainRole = Role.ROLE_ADMIN;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AuthorisedUser.id = id;
    }

    public static Role getMainRole() {
        return mainRole;
    }

    public static void setMainRole(Role mainRole) {
        AuthorisedUser.mainRole = mainRole;
    }
}
