package ru.seleand.restaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.seleand.restaurants.model.Role;
import ru.seleand.restaurants.service.UserService;
import ru.seleand.restaurants.to.UserTo;
import ru.seleand.restaurants.util.UserUtil;
import ru.seleand.restaurants.web.user.AbstractUserController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class RootController extends AbstractUserController {
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


    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                super.create(UserUtil.createNewFromTo(userTo));
                status.setComplete();
                return "redirect:login?message=app.registered";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", "exception.duplicate_email");
            }
        }
        model.addAttribute("register", true);
        return "profile";
    }

    private int getParameterInt(HttpServletRequest request, String parameterName) {
        String param = Objects.requireNonNull(request.getParameter(parameterName));
        return Integer.valueOf(param);
    }

}
