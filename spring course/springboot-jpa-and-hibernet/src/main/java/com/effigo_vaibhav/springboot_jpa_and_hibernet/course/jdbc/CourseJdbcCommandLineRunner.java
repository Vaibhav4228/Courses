package com.effigo_vaibhav.springboot_jpa_and_hibernet.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJdbcRepository repo;

    @Override
    public void run(String... args) throws Exception {
        repo.insert(1, "Azure", "VaibhavS");
        repo.insert(2, "python", "vv");
        repo.insert(3, "java", "udemy");
        repo.deleteById(1);

    }

}
