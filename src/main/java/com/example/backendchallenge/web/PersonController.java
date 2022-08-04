package com.example.backendchallenge.web;

import com.example.backendchallenge.data.Person;
import com.example.backendchallenge.data.PersonRepository;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("updatePerson/{personId}")
    private void updatePerson(@PathVariable Long personId, @RequestBody Person updatedPerson) {

        Optional<Person> personToUpdate = personRepository.findById(personId);
        if (personToUpdate.isPresent()) {

            personToUpdate.get().setFirstName(updatedPerson.getFirstName());
            personToUpdate.get().setLastName(updatedPerson.getLastName());
            personToUpdate.get().setDob(updatedPerson.getDob());
            personToUpdate.get().setDateUpdated(LocalDate.now());

            personRepository.save(personToUpdate.get());
        }
    }

    @DeleteMapping("deletePerson/{personId}")
    private void deletePerson(@PathVariable Long personId) {
        personRepository.deleteById(personId);
    }
}
