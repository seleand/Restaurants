package ru.seleand.restaurants.web;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.seleand.restaurants.RestaurantTestData.RESTAURANT_1;
import static ru.seleand.restaurants.RestaurantTestData.RESTAURANT_ID;
import static ru.seleand.restaurants.TestUtil.userAuth;
import static ru.seleand.restaurants.UserTestData.ADMIN;
import static ru.seleand.restaurants.UserTestData.USER;
import static ru.seleand.restaurants.model.BaseEntity.START_SEQ;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testDishes() throws Exception {
        mockMvc.perform(get("/dishes")
                .with(userAuth(ADMIN))
                .param("restaurantId","100002"))
               .andDo(print())
                .andExpect(view().name("adminDishList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/adminDishList.jsp"));
    }

    @Test
    public void testUnAuth() throws Exception {
        mockMvc.perform(get("/restaurants"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testRestaurants() throws Exception {
        mockMvc.perform(get("/restaurants")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("adminRestaurantList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/adminRestaurantList.jsp"));
    }
}