package ru.seleand.restaurants.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.seleand.restaurants.service.AbstractUserServiceTest;

import static ru.seleand.restaurants.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}