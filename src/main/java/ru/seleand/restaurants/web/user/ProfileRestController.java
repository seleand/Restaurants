package ru.seleand.restaurants.web.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.seleand.restaurants.AuthorizedUser;
import ru.seleand.restaurants.model.User;

import javax.validation.Valid;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    @DeleteMapping
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody User user) {
        super.update(user, AuthorizedUser.id());
    }
}