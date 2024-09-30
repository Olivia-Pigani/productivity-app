package com.productivityapp.demo.service;

import com.productivityapp.demo.domain.dto.TodoDto;
import com.productivityapp.demo.domain.dto.TodoResponseDto;
import com.productivityapp.demo.domain.entity.Todo;
import com.productivityapp.demo.domain.mapper.TodoMapper;
import com.productivityapp.demo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public TodoResponseDto saveTodo(TodoDto todoDto) {

        Todo savedTodo = todoRepository.save(todoMapper.toTodo(todoDto));
        return todoMapper.toTodoResponseDto(savedTodo);

    }

    public List<TodoResponseDto> getAllTodos() {

        return todoMapper.toTodoResponseDtoList(todoRepository.findAll());

    }

    public TodoResponseDto getTodoById(Long todoId){

        Optional<Todo> todo = todoRepository.findById(todoId);

        if(todo.isPresent()){
            return todoMapper.toTodoResponseDto(todo.get());
        }

        throw new EntityNotFoundException("there is no todo with this id");

    }
}
