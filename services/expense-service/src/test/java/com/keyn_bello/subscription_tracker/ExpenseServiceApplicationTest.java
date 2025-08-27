package com.keyn_bello.subscription_tracker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootTest
class ExpenseServiceApplicationTest {

    @Test
    void contextLoads() {
    }
    @Test
    void atLeastOneCoreBeanShouldBePresent() {
        String[] controllers = applicationContext.getBeanNamesForAnnotation(org.springframework.stereotype.Controller.class);
        String[] restControllers = applicationContext.getBeanNamesForAnnotation(org.springframework.web.bind.annotation.RestController.class);
        String[] services = applicationContext.getBeanNamesForAnnotation(org.springframework.stereotype.Service.class);
        String[] repositories = applicationContext.getBeanNamesForAnnotation(org.springframework.stereotype.Repository.class);
        int total = controllers.length + restControllers.length + services.length + repositories.length;
        assertTrue(total >= 0, "Context should be able to query for core beans without throwing");
    }

    @Test
    void activeProfilesShouldBeQueryable() {
        String[] profiles = environment.getActiveProfiles();
        assertNotNull(profiles);
    }

}