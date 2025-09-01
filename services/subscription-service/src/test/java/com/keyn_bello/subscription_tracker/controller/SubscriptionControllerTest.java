package com.keyn_bello.subscription_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyn_bello.subscription_tracker.service.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = SubscriptionController.class)
class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private SubscriptionService subscriptionService;

    @Test
    void shouldCreateSubscription_return201_withCreatedEntity() throws Exception {
        //given
        //when
        //then
    }
}