package ru.seleand.restaurants.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.seleand.restaurants.AuthorizedUser;
import ru.seleand.restaurants.model.Role;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/users")
public class JspUserController {

/*
    @RequestMapping(method = RequestMethod.POST)
    public String updateOrCreate(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        AuthorizedUser.setMainRole(Role.ROLE_ADMIN);

        return "redirect:restaurants";
    }
*/

}
