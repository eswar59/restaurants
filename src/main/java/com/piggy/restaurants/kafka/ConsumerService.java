package com.piggy.restaurants.kafka;

import com.piggy.restaurants.model.CustomerOrder;
import com.piggy.restaurants.model.User;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "foodOrder", groupId = "restaurants")
    public void consume(CustomerOrder order){
        log.info(String.format("The consumed message is %s", order.toString()));
    }
}

