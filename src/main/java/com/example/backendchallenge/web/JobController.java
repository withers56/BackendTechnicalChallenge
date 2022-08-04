package com.example.backendchallenge.web;


import com.example.backendchallenge.data.Job;
import com.example.backendchallenge.data.JobRepository;
import com.example.backendchallenge.data.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor

@CrossOrigin
@RestController
@RequestMapping(value = "/backendChallenge/jobs", headers = "Accept=application/json")
public class JobController {
     private JobRepository jobRepository;


     @GetMapping
     @JsonView(View.job.class)
     private List<Job> getJobs() {
         return jobRepository.findAll();
     }

     @GetMapping("jobsAndPeople")
     private List<Job> getJobsAndPeople() {
          return jobRepository.findAll();
     }
}
