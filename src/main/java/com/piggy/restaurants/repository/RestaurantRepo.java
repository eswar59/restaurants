package com.piggy.restaurants.repository;

import com.piggy.restaurants.rest.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
}
