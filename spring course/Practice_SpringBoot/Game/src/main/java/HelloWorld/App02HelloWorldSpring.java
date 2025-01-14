package com.gameApp.Game.HelloWorld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {
public static void main(String[] args){
    try(var context =
                new AnnotationConfigApplicationContext
                        (HelloWorldConfig.class)) {

        System.out.println(context.getBean("name"));
        System.out.println(context.getBean("age"));

        System.out.println(context.getBean("person"));

    }
}
}
