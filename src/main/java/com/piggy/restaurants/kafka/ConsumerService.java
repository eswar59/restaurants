package com.piggy.restaurants.kafka;

import com.piggy.restaurants.service.RestaurantService;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import shared.CustomerOrder;

@Service
public class ConsumerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    private RestaurantService restaurantService;

    public ConsumerService (RestaurantService restaurantService){
        this.restaurantService=restaurantService;
    }

    @KafkaListener(topics = "foodOrder", groupId = "restaurants")
    public void consume(CustomerOrder order){
        log.info(String.format("The consumed message is %s", order.toString()));
        restaurantService.restaurantOrderValidationService(order);
    }
}

