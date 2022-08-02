package com.example.backendchallenge.data;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private LocalDate dateJoined;

    @Column
    private LocalDate dateUpdated;
}
