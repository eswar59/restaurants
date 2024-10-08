package com.piggy.restaurants.repository;

import com.piggy.restaurants.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
