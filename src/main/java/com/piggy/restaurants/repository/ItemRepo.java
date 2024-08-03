package com.piggy.restaurants.repository;

import com.piggy.restaurants.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Integer> {
}
