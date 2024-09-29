package com.productivityapp.demo.controller;

import com.productivityapp.demo.service.TodoService;
import org.springframework.stereotype.Controller;

@Controller
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
}
