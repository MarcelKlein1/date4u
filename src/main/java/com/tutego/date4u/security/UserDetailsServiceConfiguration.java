package com.tutego.date4u.security;

import com.tutego.date4u.entities.Unicorn;
import com.tutego.date4u.repositories.UnicornRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserDetailsServiceConfiguration implements UserDetailsService {

    @Autowired
    UnicornRepository unicornRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Unicorn unicorn = unicornRepository.findByEmail(username);
        if (unicorn == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(unicorn);
    }
}
