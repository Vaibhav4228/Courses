package com.vaibhav_effigo.WebApp.todo;

import com.vaibhav_effigo.WebApp.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {
    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }

    private TodoService todoService;

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        model.put("name", "vaibhav");
        List<Todo> todos = todoService.findByUsername("vaibhav");
        model.addAttribute("todos", todos);

        return "listTodos";
    }


}
