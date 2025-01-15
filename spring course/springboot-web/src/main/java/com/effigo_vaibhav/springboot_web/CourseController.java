package com.effigo_vaibhav.springboot_web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @RequestMapping("/courses")
    public List<Course> gettingAllCourses(){
        return Arrays.asList(
           new Course(1, "learn aws", "xyz"),
                new Course(2, "Learn devops", "abc"),
           new Course(3, "Learn python", "vaibhav sharma")

        );
    }

}
