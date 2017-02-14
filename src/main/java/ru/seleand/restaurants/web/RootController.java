package ru.seleand.restaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.seleand.restaurants.service.DishService;
import ru.seleand.restaurants.service.RestaurantService;
import ru.seleand.restaurants.AuthorizedUser;
import ru.seleand.restaurants.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:restaurants";
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String restaurants(Model model) {
        model.addAttribute("restaurantList", restaurantService.getAll());
        return "restaurantList";
    }

}
