package ru.seleand.restaurants.web.restaurant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.seleand.restaurants.repository.RestaurantRepository;

import static org.junit.Assert.*;

/**
 * Created by Asus on 10.11.2016.
 */
@ContextConfiguration({"classpath:spring/mock.xml","classpath:spring/spring-app.xml","classpath:spring/spring-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class InMemoryRestaurantAdminRestControllerTest {

    @Autowired
    private RestaurantRestController controller;


    @Autowired
    private RestaurantRepository repository;


    @Before
    public void setUp() throws Exception {
//        repository.init();
    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

}