package com.piggy.restaurants.config;

import com.piggy.restaurants.service.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfiguration ( JpaUserDetailsService jpaUserDetailsService){
        this.jpaUserDetailsService=jpaUserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(auth-> auth.anyRequest().authenticated());
        http.csrf().disable();
        http.httpBasic(Customizer.withDefaults());
        http.userDetailsService(jpaUserDetailsService);

        return http.build();
    }

}