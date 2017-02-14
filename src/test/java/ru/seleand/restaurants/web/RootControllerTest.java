package ru.seleand.restaurants.web;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.seleand.restaurants.RestaurantTestData.RESTAURANT_1;
import static ru.seleand.restaurants.RestaurantTestData.RESTAURANT_ID;
import static ru.seleand.restaurants.UserTestData.USER;
import static ru.seleand.restaurants.model.BaseEntity.START_SEQ;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ)),
                                hasProperty("name", is(USER.getName()))
                        )
                )));
    }

    @Test
    public void testRestaurants() throws Exception {
        mockMvc.perform(get("/restaurants"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("restaurantList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/restaurantList.jsp"))
                .andExpect(model().attribute("restaurantList", hasSize(2)))
                .andExpect(model().attribute("restaurantList", hasItem(
                        allOf(
                                hasProperty("id", is(RESTAURANT_ID)),
                                hasProperty("name", is(RESTAURANT_1.getName()))
                        )
                )));
    }
}