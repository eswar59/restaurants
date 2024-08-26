package com.piggy.restaurants.service;

import com.piggy.restaurants.model.Item;
import com.piggy.restaurants.model.User;
import com.piggy.restaurants.repository.ItemRepo;
import com.piggy.restaurants.repository.RestaurantRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import shared.CustomerOrder;

import java.security.Principal;
import java.util.Optional;

@Service
public class RestaurantService {

    private ItemRepo itemRepo;
    private RestaurantRepo restaurantRepo;
    private WebSocketService webSocketService;

    private final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    public RestaurantService (ItemRepo itemRepo, RestaurantRepo restaurantRepo, WebSocketService webSocketService){
        this.itemRepo=itemRepo;
        this.restaurantRepo=restaurantRepo;
        this.webSocketService=webSocketService;
    }


    public void restaurantOrderValidationService(CustomerOrder order){
        // find restaurant id from item id and call websocket service
        // to see if restaurant is open if not send error if open
        // send order to restaurant queue sendin error from ere to customer
        // difficult so dont take order in customer backend only if closed
        Optional<Item> itemOrdered = itemRepo.findById(order.getItemId());
        // if item doesnt exist send the not exists message to consumer
        if(itemOrdered.isPresent()){
            int resaurantId = itemOrdered.get().getUser().getId();
            Optional<User> restaurant = restaurantRepo.findById(resaurantId);
            logger.info(restaurant.toString());
            // check if restaurant is opened
            if(restaurant.get().getStatus().equals("OPENED")){
                // order is present and restaurant is open means ws connection
                // is already present send the order to respective restaurant
                // in ws to respective queue to restaurant here we are not
                // responding to any controller we ourselves are sending

                webSocketService.sendOrderToRestaurant(order, restaurant.get().getUsername());
            }else{
                // send order is made to closed restaurant to kafka
                // cluster to which the customer BE is subscribed to
            }
        }else{
            // send orderId doesnt exists to kafka
            // cluster to which the customer BE is subscribed to
            // once the order is make in customer BE then we have to
            // open a ws connection there also in customers for real
            // time updates from the restaurant side kafka cluster
        }

    }

    @Transactional
    public void changeStatusOfRestaurnant(String restaurantName) {
        // change the restaurant status based on current status of restaurant
        Optional<User> rest = restaurantRepo.findByUsername(restaurantName);
        logger.info(rest.get().toString());
        if(rest.get().getStatus().equals("CLOSED")){
            rest.get().setStatus("OPENED");
            // we also have to change the data in database
            restaurantRepo.save(rest.get());
        }else {
            rest.get().setStatus("CLOSED");
            restaurantRepo.save(rest.get());
        }
        logger.info(rest.get().toString());
    }
}
