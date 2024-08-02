package com.piggy.restaurants.repository;

import com.piggy.restaurants.rest.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Integer> {
}
