package com.piggy.restaurants.controller;

import com.piggy.restaurants.repository.RestaurantRepo;
import com.piggy.restaurants.model.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    private RestaurantRepo restaurantRepo;

    public RestaurantController(RestaurantRepo repo){
        this.restaurantRepo=repo;
    }

    //getting all restaurants
    @GetMapping("/restaurants")
    public List<User> getAllRestaurants(){
        return restaurantRepo.findAll();
    }

    //get particular restaurant
//    @GetMapping("/restaurants/{id}")
//    public Restaurant getOneRestaurants(@PathVariable int id){
//        Optional<Restaurant> rest= restaurantRepo.findById(id);
//        if(rest.isEmpty()){
//            throw new RestaurantNotFoundException(id);
//        }
//        return rest.get();
//    }

    //creating a restaurant
    @PostMapping("/restaurants")
    public User createRestaurant(@Valid @RequestBody User user){
        user.setRoles("ROLE_USER");
        restaurantRepo.save(user);
        return user;
    }



}
