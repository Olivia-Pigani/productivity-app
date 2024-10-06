package com.productivityapp.demo.domain.mapper;

import com.productivityapp.demo.domain.dto.TodoDto;
import com.productivityapp.demo.domain.dto.TodoResponseDto;
import com.productivityapp.demo.domain.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoMapper {

    public Todo toTodo(TodoDto todoDto) {

        return Todo.builder()
                .title(todoDto.title())
                .description(todoDto.description())
                .publishDate(todoDto.publishDate())
                .deadLineDate(todoDto.deadLineDate())
                .priority(todoDto.priority())
                .isDone(todoDto.isDone())
                .build();

    }

    public TodoDto toTodoDto(Todo todo) {

        return new TodoDto(
                todo.getId(),
                todo.getTitle()
                , todo.getDescription()
                , todo.getPublishDate()
                , todo.getDeadLineDate()
                , todo.getPriority()
                , todo.isDone());

    }

    public TodoResponseDto toTodoResponseDto(Todo todo) {

        return new TodoResponseDto(
                todo.getId(),
                todo.getTitle()
                , todo.getDescription()
                , todo.getPublishDate()
                , todo.getDeadLineDate()
                , todo.getPriority()
                , todo.isDone());

    }

    public List<Todo> toTodoList(List<TodoDto> todoList) {

        return todoList
                .stream()
                .map(this::toTodo)
                .collect(Collectors.toList());

    }

    public List<TodoDto> toTodoDtoList(List<Todo> todoList) {

        return todoList
                .stream()
                .map(this::toTodoDto)
                .toList();

    }

    public List<TodoResponseDto> toTodoResponseDtoList(List<Todo> todoList) {

        return todoList
                .stream()
                .map(this::toTodoResponseDto)
                .toList();

    }
}
