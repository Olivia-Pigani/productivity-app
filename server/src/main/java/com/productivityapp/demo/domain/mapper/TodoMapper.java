package com.productivityapp.demo.domain.mapper;

import com.productivityapp.demo.domain.dto.TodoDto;
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
                todo.getTitle()
                , todo.getDescription()
                , todo.getPublishDate()
                , todo.getDeadLineDate()
                , todo.getPriority()
                , todo.isDone());
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
        return todoList
                .stream()
                .map(this::toTodoDto)
                .collect(Collectors.toList());
    }


}
