package com.example.backendchallenge.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "persons")
public class Person {

//    Person should have fields
//      ○ id (auto-increment)
//      ○ name
//      ○ age
//      ○ dateJoined
//      ○ dateUpdated

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private LocalDate dateJoined;

    @Column
    private LocalDate dateUpdated;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;


    @ManyToOne
    @JsonIgnoreProperties({"personsWithJob"})
    private Job job;

    public enum Role {USER, ADMIN}
}
