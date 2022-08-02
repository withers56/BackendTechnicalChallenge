package com.example.backendchallenge.web;

import com.example.backendchallenge.data.Person;
import com.example.backendchallenge.data.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor

@CrossOrigin
@RestController
@RequestMapping(value = "/backendChallenge/persons", headers = "Accept=application/json")
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping
    private List<Person> getAllPersons () {
        return personRepository.findAll();
    }
}
