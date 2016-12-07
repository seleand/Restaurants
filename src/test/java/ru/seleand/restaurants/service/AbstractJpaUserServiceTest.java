package ru.seleand.restaurants.service;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.seleand.restaurants.repository.JpaUtil;

abstract public class AbstractJpaUserServiceTest extends AbstractUserServiceTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
