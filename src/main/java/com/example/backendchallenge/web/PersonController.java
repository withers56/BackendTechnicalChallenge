package com.example.backendchallenge.web;

import com.example.backendchallenge.data.Person;
import com.example.backendchallenge.data.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("addPerson")
    private void addPerson(@RequestBody Person newPerson) {

        Person personToAdd = new Person();

        personToAdd.setDob(newPerson.getDob());
        personToAdd.setFirstName(newPerson.getFirstName());
        personToAdd.setLastName(newPerson.getLastName());
        personToAdd.setDateJoined(LocalDate.now());

        personRepository.save(personToAdd);

        System.out.println("person added");
    }
}
