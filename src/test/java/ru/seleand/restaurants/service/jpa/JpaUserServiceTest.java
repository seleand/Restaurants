package ru.seleand.restaurants.service.jpa;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.seleand.restaurants.repository.JpaUtil;
import ru.seleand.restaurants.service.AbstractJpaUserServiceTest;

import static ru.seleand.restaurants.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaUserServiceTest extends AbstractJpaUserServiceTest {

}