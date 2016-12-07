package ru.seleand.restaurants.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.seleand.restaurants.service.AbstractJpaUserServiceTest;

import static ru.seleand.restaurants.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {

}