package com.vaibhav_effigo.WebApp;


import com.vaibhav_effigo.WebApp.todo.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(1, "vaibhav","completed azure",
                LocalDate.now().plusYears(1), false ));
        todos.add(new Todo(2, "vaibhav","Learn DevOps",
                LocalDate.now().plusYears(2), false ));
        todos.add(new Todo(3, "vaibhav","Learn Full Stack Java Development",
                LocalDate.now().plusYears(3), false ));
    }
    public List<Todo> findByUsername(String username){
        return todos;
    }
}
