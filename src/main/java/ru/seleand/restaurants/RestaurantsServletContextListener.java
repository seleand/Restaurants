package ru.seleand.restaurants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Asus on 02.12.2016.
 */
public class RestaurantsServletContextListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantsServletContextListener.class);
    private ConfigurableApplicationContext springContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("init spring context");
        springContext = new ClassPathXmlApplicationContext(new String[]{"spring/spring-app.xml", "spring/spring-db.xml"}, false);
        springContext.getEnvironment().setActiveProfiles(Profiles.ACTIVE_DB, Profiles.DB_IMPLEMENTATION);
        springContext.refresh();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("close spring context");
        springContext.close();
    }
}
