package ru.seleand.restaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.seleand.restaurants.model.Role;
import ru.seleand.restaurants.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class RootController {
/*
    @Autowired
    private RestaurantService restaurantService;
*/

/*
    @Autowired
    private DishService dishService;
*/

/*
    @Autowired
    private UserService userService;
*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:restaurants";
    }

    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        int restaurantId = getParameterInt(request,"restaurantId");
//        model.addAttribute("dishList", dishService.getAll(restaurantId));
        model.addAttribute("restaurantId",restaurantId);
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ROLE_ADMIN)){
            return "adminDishList";
        }
        return "userDishList";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.put("error", error);
        model.put("message", message);
        return "login";
    }

/*
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }
*/

/*
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:restaurants";
    }
*/

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String restaurants(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ROLE_ADMIN)){
            return "adminRestaurantList";
        }
        return "userRestaurantList";
    }
    private int getParameterInt(HttpServletRequest request, String parameterName) {
        String param = Objects.requireNonNull(request.getParameter(parameterName));
        return Integer.valueOf(param);
    }

}
