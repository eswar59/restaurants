package com.piggy.restaurants.controller;

import com.piggy.restaurants.repository.ItemRepo;
import com.piggy.restaurants.repository.RestaurantRepo;
import com.piggy.restaurants.rest.Item;
import com.piggy.restaurants.rest.Restaurant;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    // getting all items of a restaurant as a list
    @GetMapping("/restaurants/{id}/items")
    public List<Item> getItemsOfRestaurant(@PathVariable int id){
        Optional<Restaurant> rest = restaurantRepo.findById(id);
        return rest.get().getItems();
    }

    // adding an item to list by a restaurant
    @PostMapping("/restaurants/{id}/items")
    public Item creatingItems(@Valid @RequestBody Item item, @PathVariable int id){
        Optional<Restaurant> rest = restaurantRepo.findById(id);
        item.setRestaurant(rest.get());
        itemRepo.save(item);
        return item;
    }
}
