package com.example.backendchallenge.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.job.class})
    private Long id;

    @Column(nullable = false)
    @JsonView({View.job.class})
    private String jobTitle;

    @Column(nullable = false)
    @JsonView({View.job.class})
    private Double salary;


    @OneToMany(mappedBy = "job")
    @JsonIgnoreProperties({"job"})
    private Collection<Person> personsWithJob;
}
