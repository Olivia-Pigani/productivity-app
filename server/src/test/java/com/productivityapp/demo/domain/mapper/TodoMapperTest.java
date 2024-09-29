package com.productivityapp.demo.domain.mapper;

import com.productivityapp.demo.domain.dto.TodoDto;
import com.productivityapp.demo.domain.entity.Todo;
import com.productivityapp.demo.domain.enumeration.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TodoMapperTest {

    private TodoMapper todoMapper;
    private List<TodoDto> todoDtoListForTest;
    private List<Todo> todoListForTest;

    @BeforeEach
    void setUp() {
        todoMapper = new TodoMapper();
        TodoDto[] todoDtoArray = {
                new TodoDto("title1", "description1", "22/06/2024", "02/05/2028", Priority.HIGH, false),
                new TodoDto("title2", "description2", "22/06/2004", "02/05/2021", Priority.MEDIUM, false)
        };
        Todo[] todoArray = {
                new Todo(0L, "title3", "description3", "14/05/1996", "03/10/2035", Priority.LOW, true),
                new Todo(1L, "title4", "description4", "21/05/2015", "02/01/2027", Priority.MEDIUM, false)
        };
        todoDtoListForTest = new ArrayList<>(Arrays.asList(todoDtoArray));
        todoListForTest = new ArrayList<>(Arrays.asList(todoArray));
    }

    @Test
    public void should_map_dto_to_todo() {
        //GIVEN
        TodoDto todoDto = todoDtoListForTest.getFirst();

        //WHEN
        Todo todo = todoMapper.toTodo(todoDto);


        //THEN
        assertEquals(todoDto.title(), todo.getTitle());
        assertEquals(todoDto.description(), todo.getDescription());
        assertEquals(todoDto.publishDate(), todo.getPublishDate());
        assertEquals(todoDto.deadLineDate(), todo.getDeadLineDate());
        assertEquals(todoDto.priority(), todo.getPriority());
        assertEquals(todoDto.isDone(), todo.isDone());

    }

    @Test
    public void should_map_todoDtoList_to_todoList() {
        //WHEN
        List<Todo> todoList = todoMapper.toTodoList(todoDtoListForTest);

        //THEN
        assertEquals(todoList.size(), todoDtoListForTest.size());
        assertEquals(todoList.getFirst().getTitle(), todoDtoListForTest.getFirst().title());

    }

    @Test
    public void  should_map_todo_to_todoDto() {
        //GIVEN
        Todo todo = todoListForTest.getFirst();

        //WHEN
        TodoDto todoDto = todoMapper.toTodoDto(todo);

        //WHEN
        assertEquals(todoDto.title(), todo.getTitle());
        assertEquals(todoDto.description(), todo.getDescription());
        assertEquals(todoDto.publishDate(), todo.getPublishDate());
        assertEquals(todoDto.deadLineDate(), todo.getDeadLineDate());
        assertEquals(todoDto.priority(), todo.getPriority());
        assertEquals(todoDto.isDone(), todo.isDone());
    }

    @Test
    public void should_method_toTodoList_throw_nullPointerException_if_todoDtoList_is_null(){
        //GIVEN
        List<TodoDto> todoDtoList = new ArrayList<>();

        //THEN
        NullPointerException errorMessage = assertThrows(NullPointerException.class, ()-> todoMapper.toTodoList(todoDtoList));
        assertEquals(errorMessage.getMessage(),"the todo list is empty !");
    }

}
