package com.productivityapp.demo.service;

import com.productivityapp.demo.domain.dto.TodoDto;
import com.productivityapp.demo.domain.dto.TodoResponseDto;
import com.productivityapp.demo.domain.entity.Todo;
import com.productivityapp.demo.domain.enumeration.TimeSpace;
import com.productivityapp.demo.domain.mapper.TodoMapper;
import com.productivityapp.demo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        Optional<List<Todo>> todoList = Optional.of(todoRepository.findAll());

        return todoMapper.toTodoResponseDtoList(todoList.get());

    }

    public TodoResponseDto getTodoById(Long todoId) {

        Optional<Todo> todo = todoRepository.findById(todoId);

        return todoMapper.toTodoResponseDto(todo.get());

    }

    public TodoResponseDto updateTodo(Long todoId, TodoDto newTodoDtoData) {

        Optional<Todo> todoOpt = todoRepository.findById(todoId);

        if (todoOpt.isPresent()) {

            Todo todo = todoOpt.get();

            todo.setTitle(newTodoDtoData.title());
            todo.setDescription(newTodoDtoData.description());
            todo.setPublishDate(newTodoDtoData.publishDate());
            todo.setDeadLineDate(newTodoDtoData.deadLineDate());
            todo.setPriority(newTodoDtoData.priority());
            todo.setDone(newTodoDtoData.isDone());

            return todoMapper.toTodoResponseDto(todoRepository.save(todo));
        }

        throw new EntityNotFoundException("there is no todo with this id");

    }

    public boolean deleteTodoById(Long todoId) {

        System.out.println("szszs");

        Optional<Todo> todo = todoRepository.findById(todoId);

        if (todo.isPresent()) {
            todoRepository.delete(todo.get());
            return true;
        } else {
           return false;
        }
    }

    public List<TodoResponseDto> getAllTodosByTimeSpace(TimeSpace timeSpace){

        Optional<List<Todo>> todoListOpt = Optional.empty();

        switch (timeSpace) {

            case PRESENT -> todoListOpt = Optional.ofNullable(todoRepository.findAllByDeadLineDate(LocalDate.now()));

            case FUTURE -> todoListOpt = Optional.ofNullable(todoRepository.findTodoByDeadLineDateAfter(LocalDate.now()));

            case PAST -> todoListOpt = Optional.ofNullable(todoRepository.findTodoByDeadLineDateBefore(LocalDate.now()));

        }

        if (todoListOpt.isPresent()){
            return todoMapper.toTodoResponseDtoList(todoListOpt.get());
        } else {
            return List.of();
        }
    }

    public TodoResponseDto updateTodoStatusById(Long todoId, Boolean newStatus){
        Optional<Todo> todoToUpdate = todoRepository.findById(todoId);

        if (todoToUpdate.isPresent()){
            Todo todo = todoToUpdate.get();
            todo.setDone(newStatus);
            todoRepository.save(todo);
            return todoMapper.toTodoResponseDto(todo);
        } else {

        }
        throw new EntityNotFoundException("there is no todo with this id");

    }
}
