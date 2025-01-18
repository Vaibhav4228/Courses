package com.vaibhav_effigo.rest_api.helloWorldApi;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello, I am HELLO World Api..";

    }
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean ("Hello, I am HELLO World Bean Api..");

    }

    @GetMapping("/hello-world-bean-internationalized")
    public String helloWorldinternationalized(){

        Locale locale = LocaleContextHolder.getLocale();
      return  messageSource.getMessage("good.morning.message", null, "Default Message", locale);


    }

}
