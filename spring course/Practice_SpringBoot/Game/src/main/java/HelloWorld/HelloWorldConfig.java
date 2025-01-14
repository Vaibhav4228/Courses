package com.gameApp.Game.HelloWorld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


record Person(String name, int age){

}
record Address(String firstLine, String city, String Address){

}
@Configuration
public class HelloWorldConfig {

    @Bean
    public String name(){
        return "Vaibhav";
    }

    @Bean
    public int age(){
        return 22;
    }

    @Bean
    public Person person(){
        var person = new Person("vaibhav", 21);
        return person;
    }

    //Autoconfigure bean
    @Bean
    public Person person2(){
        return new Person(name(),age() );
    }


}
