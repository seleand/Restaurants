package ru.seleand.restaurants.web.dish;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.seleand.restaurants.TestUtil;
import ru.seleand.restaurants.model.Dish;
import ru.seleand.restaurants.service.DishService;
import ru.seleand.restaurants.web.AbstractControllerTest;
import ru.seleand.restaurants.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.seleand.restaurants.RestaurantTestData.RESTAURANT_1;
import static ru.seleand.restaurants.RestaurantTestData.RESTAURANT_ID;
import static ru.seleand.restaurants.DishTestData.*;
import static ru.seleand.restaurants.TestUtil.userHttpBasic;
import static ru.seleand.restaurants.UserTestData.ADMIN;
import static ru.seleand.restaurants.UserTestData.USER;

/**
 * Created by Asus on 14.02.2017.
 */
public class DishRestControllerTest extends AbstractControllerTest{

    public static final String REST_URL = DishRestController.REST_URL+"/";

    @Autowired
    protected DishService dishService;

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL+RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(DISH_1, DISH_2, DISH_3)));

    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL+RESTAURANT_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL+RESTAURANT_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL+RESTAURANT_ID+"/dish/"+DISH_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(DISH_1));

    }

    @Test
    public void testCreate() throws Exception {
        Dish expected = new Dish(LocalDate.now(), "New", 5000, RESTAURANT_1);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        Dish returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(expected, DISH_1, DISH_2, DISH_3), dishService.getAll(RESTAURANT_ID));

    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL+RESTAURANT_ID+"/dish/"+DISH_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_2, DISH_3), dishService.getAll(RESTAURANT_ID));

    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = new Dish(DISH_ID, LocalDate.of(2016,11,13),"dish 1 rest1",5000);
        updated.setDescription("UpdatedDescription");
        mockMvc.perform(put(REST_URL+RESTAURANT_ID+"/dish/"+DISH_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, dishService.get(DISH_ID, RESTAURANT_ID));

    }

}