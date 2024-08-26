package com.piggy.restaurants.controller;

import com.piggy.restaurants.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController {

    private RestaurantService restaurantService;
    private final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    public WebSocketController(RestaurantService restaurantService){
        this.restaurantService=restaurantService;
    }

    @MessageMapping("restaurantStatusChange")
    public void changeRestaurantStatus(@Payload Object payload, Principal principal){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String restaurantName = null;
//
//        if (authentication != null) {
//            if (authentication.getPrincipal() instanceof UserDetails) {
//                restaurantName = ((UserDetails) authentication.getPrincipal()).getUsername();
//            } else {
//                restaurantName = authentication.getPrincipal().toString();
//            }
//        }
        logger.info("we are at start of controller");
        String restaurantName =principal.getName();
        restaurantService.changeStatusOfRestaurnant(restaurantName);
        logger.info(restaurantName+"we are at end of controller");
    }

    @MessageMapping("modifiedOrder")
    public void addUser(){

    }
}
