package com.productivityapp.demo.domain.mapper;

import com.productivityapp.demo.domain.dto.TodoDto;
import com.productivityapp.demo.domain.dto.TodoResponseDto;
import com.productivityapp.demo.domain.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoMapper {

    public Todo toTodo(TodoDto todoDto) {
        if (todoDto != null){
            return Todo.builder()
                    .title(todoDto.title())
                    .description(todoDto.description())
                    .publishDate(todoDto.publishDate())
                    .deadLineDate(todoDto.deadLineDate())
                    .priority(todoDto.priority())
                    .isDone(todoDto.isDone())
                    .build();
        }else {
            throw new NullPointerException("the todoDto must not be null");
        }
    }

    public TodoDto toTodoDto(Todo todo) {
        if (todo != null){
            return new TodoDto(
                    todo.getTitle()
                    , todo.getDescription()
                    , todo.getPublishDate()
                    , todo.getDeadLineDate()
                    , todo.getPriority()
                    , todo.isDone());
        }else {
            throw new NullPointerException("the todo must not be null");
        }
    }

    public TodoResponseDto toTodoResponseDto(Todo todo) {
        if (todo != null){
            return new TodoResponseDto(
                    todo.getTitle()
                    , todo.getDescription()
                    , todo.getPublishDate()
                    , todo.getDeadLineDate()
                    , todo.getPriority()
                    , todo.isDone());
        }else {
            throw new NullPointerException("the todo must not be null");
        }
    }

    public List<Todo> toTodoList(List<TodoDto> todoList) {
        if (!todoList.isEmpty()){
            return todoList
                    .stream()
                    .map(this::toTodo)
                    .collect(Collectors.toList());
        } else{
          throw new NullPointerException("the todo list is empty !");
        }
    }

    public List<TodoDto> toTodoDtoList(List<Todo> todoList) {
        if (!todoList.isEmpty()){
            return todoList
                    .stream()
                    .map(this::toTodoDto)
                    .toList();
        }else {
            throw new NullPointerException("the todoList list is empty !");
        }
    }

    public List<TodoResponseDto> toTodoResponseDtoList(List<Todo> todoList) {
        if (!todoList.isEmpty()){
            return todoList
                    .stream()
                    .map(this::toTodoResponseDto)
                    .toList();
        }else{
            throw new NullPointerException("the todoList list is empty !");
        }
    }
}
