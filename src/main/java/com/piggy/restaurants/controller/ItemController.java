package com.piggy.restaurants.controller;


import com.piggy.restaurants.exception.UserIdNotExistsException;
import com.piggy.restaurants.repository.ItemRepo;
import com.piggy.restaurants.repository.RestaurantRepo;
import com.piggy.restaurants.model.Item;
import com.piggy.restaurants.model.User;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.piggy.restaurants.exception.NotAuthorizedException;

import java.nio.channels.FileLockInterruptionException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    private RestaurantRepo restaurantRepo;
    private ItemRepo itemRepo;


    public ItemController(RestaurantRepo repo, ItemRepo itemRepo){
        this.restaurantRepo=repo;
        this.itemRepo=itemRepo;
    }

    // testing get mapping error
//    @GetMapping("/public/test")
//    public String test() throws FileLockInterruptionException{
//        throw new FileLockInterruptionException();
//    }

    // getting all items of a restaurant as a list
    @GetMapping("/public/restaurants/{id}/items")
    public List<Item> getItemsOfRestaurant(@PathVariable int id){
        Optional<User> rest = restaurantRepo.findById(id);
        if(rest.isEmpty()){
            throw new UserIdNotExistsException("No user exists with id "+ id);
        }
        return rest.get().getItems();
    }

    // adding an item to list by a restaurant
    @PostMapping("/restaurants/{id}/items")
    public Item creatingItems(@Valid @RequestBody Item item, @PathVariable int id, Principal principal){
        Optional<User> rest = restaurantRepo.findById(id);
        if(rest.isEmpty()){
            throw new UserIdNotExistsException("No restaurant exists with id "+ id);
        }
        String loggedInUsername = principal.getName();
        if(loggedInUsername.equals(rest.get().getUsername())){
            item.setUser(rest.get());
            itemRepo.save(item);
            return item;
        }
        else{
            //return null;
            throw new NotAuthorizedException("You are not authorized to make changes to items of restaurant with id:"+ id);
        }
    }
}
