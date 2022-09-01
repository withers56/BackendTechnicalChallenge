package com.example.backendchallenge.web;

import com.example.backendchallenge.data.Job;
import com.example.backendchallenge.data.JobRepository;
import com.example.backendchallenge.data.Person;
import com.example.backendchallenge.data.PersonRepository;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
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
    private final JobRepository jobRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    private List<Person> getAllPersons () {
        return personRepository.findAll();
    }

    @GetMapping("personInfo")
    private Person getPersonLoggedIn(OAuth2Authentication auth) {
        return personRepository.findByUsername(auth.getName());
    }

    @PostMapping("addPerson")
    private void addPerson(@RequestBody Person newPerson) {

        Person personToAdd = new Person();

        personToAdd.setUsername(newPerson.getUsername());

        String encryptedPassword = passwordEncoder.encode(newPerson.getPassword());
        personToAdd.setPassword(encryptedPassword);

        personToAdd.setDob(newPerson.getDob());
        personToAdd.setFirstName(newPerson.getFirstName());
        personToAdd.setLastName(newPerson.getLastName());
        personToAdd.setDateJoined(LocalDate.now());
        personToAdd.setRole(Person.Role.USER);

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

    @PutMapping("updateJob")
    private void updatePersonJob(@RequestParam Long personId, @RequestParam Long jobId) {
        System.out.println("Persons id: " + personId);
        System.out.println("Jobs id: " + jobId);

        Optional<Person> personToUpdate = personRepository.findById(personId);
        Optional<Job> jobToAdd = jobRepository.findById(jobId);

        personToUpdate.get().setJob(jobToAdd.get());

        personRepository.save(personToUpdate.get());
    }

    @DeleteMapping("deletePerson/{personId}")
    private void deletePerson(@PathVariable Long personId) {
        personRepository.deleteById(personId);
    }
}
