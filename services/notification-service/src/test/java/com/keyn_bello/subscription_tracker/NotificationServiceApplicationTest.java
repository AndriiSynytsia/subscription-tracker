package com.keyn_bello.subscription_tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

package com.keyn_bello.subscription_tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotificationServiceApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void contextLoads() {
        Assertions.assertThat(applicationContext).as("ApplicationContext should be initialized").isNotNull();
        Assertions.assertThat(objectMapper).as("ObjectMapper should be auto-configured").isNotNull();
        Assertions.assertThat(applicationContext.getBeanDefinitionCount()).as("There should be registered beans").isGreaterThan(0);

    }
}