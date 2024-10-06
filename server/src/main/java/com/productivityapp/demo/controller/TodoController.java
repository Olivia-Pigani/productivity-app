package com.productivityapp.demo.controller;

import com.productivityapp.demo.domain.dto.TodoDto;
import com.productivityapp.demo.domain.dto.TodoResponseDto;
import com.productivityapp.demo.domain.enumeration.TimeSpace;
import com.productivityapp.demo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/add")
    public ResponseEntity<TodoResponseDto> saveTodo(@RequestBody TodoDto newTodoDto) {
        return new ResponseEntity<>(todoService.saveTodo(newTodoDto), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<TodoResponseDto>> getAllTodos() {
        return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
    }

    @GetMapping("/timespace/{timeSpace}")
    public ResponseEntity<List<TodoResponseDto>> getAllTodosByTimeSpace(@PathVariable String timeSpace){
        System.out.println("method time called");
        return new ResponseEntity<>(todoService.getAllTodosByTimeSpace(TimeSpace.valueOf(timeSpace.toUpperCase())),HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long todoId) {
        return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> updateTodoById(@PathVariable Long todoId, @RequestBody TodoDto todoToAdd) {
        return new ResponseEntity<>(todoService.updateTodo(todoId, todoToAdd), HttpStatus.CREATED);
    }

}
