package com.effigo_vaibhav.springboot_jpa_and_hibernet.CourseJpa;

import com.effigo_vaibhav.springboot_jpa_and_hibernet.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoourseJpaCommandLineRunner {

    @Autowired
    private CourseJpaRepository repo;

    public void run(String... args) throws Exception{
        repo.save(new Course(11, "AI ML", "Vaibhav"));
        repo.save(new Course(22, "Azure", "wwee"));
        repo.save(new Course(2, "AWS", "kafka"));

        repo.deleteById(1L);
    }


}
