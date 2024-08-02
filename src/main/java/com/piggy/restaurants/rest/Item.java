package com.piggy.restaurants.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private int id;

    @Size(min=3)
    private String itemName;

    @Min(50)
    private int cost;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private Restaurant restaurant;

    public Item(int id, String itemName, int cost) {
        this.id = id;
        this.itemName = itemName;
        this.cost = cost;
    }

    public Item(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Size(min = 3)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(@Size(min = 3) String itemName) {
        this.itemName = itemName;
    }

    @Min(50)
    public int getCost() {
        return cost;
    }

    public void setCost(@Min(50) int cost) {
        this.cost = cost;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
