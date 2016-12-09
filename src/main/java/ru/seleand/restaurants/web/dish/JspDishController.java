package ru.seleand.restaurants.web.dish;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.seleand.restaurants.model.Dish;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Objects;

@Controller
@RequestMapping(value = "/dishes")
public class JspDishController extends AbstractDishController {

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        int restaurantId = getParameterInt(request,"restaurantId");
        model.addAttribute("dishList", super.getAll(restaurantId));
        model.addAttribute("restaurantId",restaurantId);
        return "dishList";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request) {
        int id = getParameterInt(request, "id");
        int restaurantId = getParameterInt(request, "restaurantId");
        super.delete(id, restaurantId);
        return "redirect:/dishes?restaurantId="+restaurantId;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Model model) {
        int restaurantId = getParameterInt(request,"restaurantId");
        final Dish dish = super.get(getParameterInt(request, "id"), restaurantId);
        model.addAttribute("dish", dish);
        model.addAttribute("restaurantId", restaurantId);
        return "dishEdit";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(HttpServletRequest request, Model model) {
        int restaurantId = getParameterInt(request,"restaurantId");
        final Dish dish = new Dish(LocalDate.now());
        model.addAttribute("dish", dish);
        model.addAttribute("restaurantId", restaurantId);
        return "dishEdit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateOrCreate(HttpServletRequest request) {
//    public String updateOrCreate(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        int restaurantId = getParameterInt(request,"restaurantId");
        double priceDouble = Double.valueOf(request.getParameter("price"))*100;
        int price = (int) priceDouble;
        Dish dish= new Dish(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDate.parse(request.getParameter("date")),
                request.getParameter("description"),
                price);
        super.save(dish,restaurantId);
//        model.addAttribute("restaurantId", restaurantId);
//        return "dishList?restaurantId="+restaurantId;
        return "redirect:/dishes?restaurantId="+restaurantId;
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    private int getParameterInt(HttpServletRequest request, String parameterName) {
        String param = Objects.requireNonNull(request.getParameter(parameterName));
        return Integer.valueOf(param);
    }

}
