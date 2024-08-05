package com.piggy.restaurants.controller;

import com.piggy.restaurants.repository.RestaurantRepo;
import com.piggy.restaurants.model.User;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    private RestaurantRepo restaurantRepo;
    private PasswordEncoder passwordEncoder;

    public RestaurantController(RestaurantRepo repo, PasswordEncoder passwordEncoder){
        this.restaurantRepo=repo;
        this.passwordEncoder=passwordEncoder;
    }

    //getting all restaurants
    @GetMapping("/public/restaurants")
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
    @PostMapping("/public/restaurants")
    public User createRestaurant(@Valid @RequestBody User user){
        user.setRoles("ROLE_RESTAURANT");
        String plainPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(plainPassword));
        restaurantRepo.save(user);
        return user;
    }



}
