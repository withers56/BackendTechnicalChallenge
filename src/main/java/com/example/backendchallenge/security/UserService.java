package com.example.backendchallenge.security;


import com.example.backendchallenge.data.Person;
import com.example.backendchallenge.data.PersonRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService implements UserDetailsService {

    private final PersonRepository repository;

    public UserService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = repository.findByUsername(username); //.orElseThrow(() -> new RuntimeException("User not found: " + email));
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
    }
}
