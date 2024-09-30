package com.productivityapp.demo.service;

import com.productivityapp.demo.domain.dto.TodoDto;
import com.productivityapp.demo.domain.dto.TodoResponseDto;
import com.productivityapp.demo.domain.entity.Todo;
import com.productivityapp.demo.domain.mapper.TodoMapper;
import com.productivityapp.demo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
