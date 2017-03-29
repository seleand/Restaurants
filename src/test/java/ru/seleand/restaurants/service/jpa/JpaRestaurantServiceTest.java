package ru.seleand.restaurants.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.seleand.restaurants.service.AbstractRestaurantServiceTest;

import static ru.seleand.restaurants.Profiles.JPA;

/**
 * Created by Asus on 29.03.2017.
 */
@ActiveProfiles(JPA)
public class JpaRestaurantServiceTest extends AbstractRestaurantServiceTest {
}
