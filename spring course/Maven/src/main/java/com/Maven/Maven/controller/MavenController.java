package com.Maven.Maven.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MavenController {

    @GetMapping("/learn")
    public String Learn(){
        return "Learning maven 😄😄😃😃..";
    }

}
