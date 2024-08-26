package com.piggy.restaurants.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import shared.CustomerOrder;

import java.security.Principal;

@Service
public class WebSocketService {

    private final SimpMessageSendingOperations messagingTemplate;
    private final Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    public WebSocketService(SimpMessageSendingOperations messagingTemplate){
        this.messagingTemplate=messagingTemplate;
    }

    public void sendOrderToRestaurant(CustomerOrder order, String restaurantName) {
        logger.info("we are in controller responsible to send message to restaurant");
        messagingTemplate.convertAndSendToUser(restaurantName, "/queue/orders", order);
    }
}
