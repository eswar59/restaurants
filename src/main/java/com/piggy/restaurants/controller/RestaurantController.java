package com.piggy.restaurants.controller;

import com.piggy.restaurants.repository.RestaurantRepo;
import com.piggy.restaurants.rest.Restaurant;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
public class RestaurantController {

    private RestaurantRepo restaurantRepo;

    public RestaurantController(RestaurantRepo repo){
        this.restaurantRepo=repo;
    }

    //getting all restaurants
    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants(){
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
    public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurant){
        restaurantRepo.save(restaurant);
        return restaurant;
    }



}
