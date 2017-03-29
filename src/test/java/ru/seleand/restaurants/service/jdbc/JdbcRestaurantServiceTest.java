package ru.seleand.restaurants.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.seleand.restaurants.service.AbstractRestaurantServiceTest;

import static ru.seleand.restaurants.Profiles.JDBC;

/**
 * Created by Asus on 29.03.2017.
 */
@ActiveProfiles(JDBC)
public class JdbcRestaurantServiceTest extends AbstractRestaurantServiceTest {
}
