package com.project.Mappers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@SpringBootApplication
public class MappersApplication implements WebMvcConfigurer {

	public static void main(String[] args) {

		SpringApplication.run(MappersApplication.class, args);
	}


}
