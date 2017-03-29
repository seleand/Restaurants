package ru.seleand.restaurants.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.seleand.restaurants.service.AbstractDishServiceTest;

import static ru.seleand.restaurants.Profiles.DATAJPA;

/**
 * Created by Asus on 29.03.2017.
 */
@ActiveProfiles(DATAJPA)
public class DataJpaDishServiceTest extends AbstractDishServiceTest {
}
