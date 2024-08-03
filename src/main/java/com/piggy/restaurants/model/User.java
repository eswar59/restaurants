package com.piggy.restaurants.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name= "Restaurant_Details")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Size(min = 2)
    private String username;

    @Size(min=3)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    //private String roles;

    @OneToMany(mappedBy = "user")
    //@JsonIgnore
    private List<Item> items;

    @Size(min=4)
    private String roles;

    public User(){

    }

    public User(int id, String name, String password, String roles) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.roles=roles;
    }

    public @Size(min = 3) String getPassword() {
        return password;
    }
    public void setPassword(@Size(min = 3) String password) {
        this.password = password;
    }

    public @Size(min = 2) String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 2) String username) {
        this.username = username;
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

    public @Size(min = 4) String getRoles() {
        return roles;
    }

    public void setRoles(@Size(min = 4) String roles) {
        this.roles = roles;
    }
}
