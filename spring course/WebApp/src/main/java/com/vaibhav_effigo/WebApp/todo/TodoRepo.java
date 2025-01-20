package com.vaibhav_effigo.WebApp.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepo extends JpaRepository<Todo, Integer> {

    List<Todo> findByUsername(String username);

}