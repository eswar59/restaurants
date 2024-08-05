package com.piggy.restaurants.service;

import com.piggy.restaurants.model.UserSecurity;
import com.piggy.restaurants.repository.RestaurantRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService{

    private RestaurantRepo restaurantRepo;

    public JpaUserDetailsService(RestaurantRepo restaurantRepo){
        this.restaurantRepo=restaurantRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return restaurantRepo.findByUsername(username)
                            .map(UserSecurity::new)
                            .orElseThrow(()-> new UsernameNotFoundException("The restaurant not found is:" + username));
    }
}
