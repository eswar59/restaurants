package com.piggy.restaurants.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name= "Restaurant_Details")
public class Restaurant {
    @Id
    @GeneratedValue
    private int id;

    @Size(min = 2)
    private String name;

    @Size(min=3)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "restaurant")
    //@JsonIgnore
    private List<Item> items;

    public Restaurant(){

    }

    public Restaurant(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public @Size(min = 3) String getPassword() {
        return password;
    }
    public void setPassword(@Size(min = 3) String password) {
        this.password = password;
    }

    public @Size(min = 2) String getName() {
        return name;
    }

    public void setName(@Size(min = 2) String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
