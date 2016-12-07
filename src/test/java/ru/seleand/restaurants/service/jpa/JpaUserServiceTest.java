package ru.seleand.restaurants.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.seleand.restaurants.service.AbstractJpaUserServiceTest;

import static ru.seleand.restaurants.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaUserServiceTest extends AbstractJpaUserServiceTest {

}