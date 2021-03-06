package ru.seleand.restaurants.web.restaurant;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.seleand.restaurants.TestUtil;
import ru.seleand.restaurants.model.Restaurant;
import ru.seleand.restaurants.service.RestaurantService;
import ru.seleand.restaurants.web.AbstractControllerTest;
import ru.seleand.restaurants.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.seleand.restaurants.RestaurantTestData.*;
import static ru.seleand.restaurants.TestUtil.userHttpBasic;
import static ru.seleand.restaurants.UserTestData.ADMIN;
import static ru.seleand.restaurants.UserTestData.USER;

/**
 * Created by Asus on 13.02.2017.
 */
public class RestaurantRestControllerTest extends AbstractControllerTest{

    public static final String REST_URL = RestaurantRestController.REST_URL+"/";

    @Autowired
    protected RestaurantService restaurantService;

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_TO.contentListMatcher(RESTAURANT_TO_1, RESTAURANT_TO_2)));

    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL+RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(RESTAURANT_1));

    }

    @Test
    public void testCreate() throws Exception {
        Restaurant expected = new Restaurant(null, "New");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        Restaurant returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(expected, RESTAURANT_1, RESTAURANT_2), restaurantService.getAll());

    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Collections.singletonList(RESTAURANT_2), restaurantService.getAll());

    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_ID,RESTAURANT_1.getName());
        updated.setName("UpdatedName");
        mockMvc.perform(put(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, restaurantService.get(RESTAURANT_ID));

    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }


}