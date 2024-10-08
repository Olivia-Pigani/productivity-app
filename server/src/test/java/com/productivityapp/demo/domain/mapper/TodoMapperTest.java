package com.productivityapp.demo.domain.mapper;

import com.productivityapp.demo.domain.dto.TodoDto;
import com.productivityapp.demo.domain.dto.TodoResponseDto;
import com.productivityapp.demo.domain.entity.Todo;
import com.productivityapp.demo.domain.enumeration.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
                new TodoDto("title1", "description1", LocalDate.parse("22/06/2024"), LocalDate.parse("02/05/2028"), Priority.HIGH, false),
                new TodoDto("title2", "description2", LocalDate.parse("22/06/2004"), LocalDate.parse("02/05/2021"), Priority.MEDIUM, false)
        };
        Todo[] todoArray = {
                new Todo(0L, "title3", "description3", LocalDate.parse("14/05/1996"), LocalDate.parse("03/10/2035"), Priority.LOW, true),
                new Todo(1L, "title4", "description4", LocalDate.parse("21/05/2015"), LocalDate.parse("02/01/2027"), Priority.MEDIUM, false)
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

    @Test
    public void should_method_toTodo_throw_nullPointerException_if_todoDto_is_null(){
        //GIVEN
        TodoDto todoDto = null;

        //THEN
        NullPointerException errorMessage = assertThrows(NullPointerException.class, ()-> todoMapper.toTodo(todoDto));
        assertEquals(errorMessage.getMessage(),"the todoDto must not be null");
    }

    @Test
    public void should_method_toTodoDto_throw_nullPointerException_if_todo_is_null(){
        //GIVEN
        Todo todo = null;

        //THEN
        NullPointerException errorMessage = assertThrows(NullPointerException.class, ()-> todoMapper.toTodoDto(todo));
        assertEquals(errorMessage.getMessage(),"the todo must not be null");
    }

    @Test
    public void should_method_toTodoDtoList_throw_nullPointerException_if_todoList_is_null() {
        //GIVEN
        List<Todo> todos = new ArrayList<>();

        //THEN
        NullPointerException errorMessage = assertThrows(NullPointerException.class, () -> todoMapper.toTodoDtoList(todos));
        assertEquals(errorMessage.getMessage(), "the todoList list is empty !");
    }

    @Test
    public void should_TodoDtoList_be_immutable(){
        //GIVEN
        List<Todo> todos = todoListForTest;

        //WHEN
        List<TodoDto> todoDtos = todoMapper.toTodoDtoList(todos);

        //THEN
        assertThrows(UnsupportedOperationException.class,() -> todoDtos.add(2,new TodoDto("title8","description8",LocalDate.parse("22/05/1995"),LocalDate.parse("15/02/2023"),Priority.HIGH,true)));
    }

    @Test
    public void should_map_todo_to_todoResponseDto() {
        //GIVEN
        Todo todo = todoListForTest.getFirst();

        //WHEN
        TodoResponseDto todoResponseDto = todoMapper.toTodoResponseDto(todo);


        //THEN
        assertEquals(todoResponseDto.title(), todo.getTitle());
        assertEquals(todoResponseDto.description(), todo.getDescription());
        assertEquals(todoResponseDto.publishDate(), todo.getPublishDate());
        assertEquals(todoResponseDto.deadLineDate(), todo.getDeadLineDate());
        assertEquals(todoResponseDto.priority(), todo.getPriority());
        assertEquals(todoResponseDto.isDone(), todo.isDone());

    }
    @Test
    public void should_method_toTodoResponseDto_throw_nullPointerException_if_todo_is_null(){
        //GIVEN
        Todo todo = null;

        //THEN
        NullPointerException errorMessage = assertThrows(NullPointerException.class, ()-> todoMapper.toTodoResponseDto(todo));
        assertEquals(errorMessage.getMessage(),"the todo must not be null");
    }



    @Test
    public void should_map_todoList_to_todoResponseDtoList() {
        //WHEN
        List<TodoResponseDto> todoResponseDtoList = todoMapper.toTodoResponseDtoList(todoListForTest);

        //THEN
        assertEquals(todoResponseDtoList.size(), todoDtoListForTest.size());
        assertEquals(todoResponseDtoList.getFirst().title(), todoListForTest.getFirst().getTitle());
    }

    @Test
    public void should_method_toTodoResponseDtoList_throw_NullPointerException_if_todoList_is_null() {
        //GIVEN
        List<Todo> todos = new ArrayList<>();

        //THEN
        NullPointerException errorMessage = assertThrows(NullPointerException.class, () -> todoMapper.toTodoResponseDtoList(todos));
        assertEquals(errorMessage.getMessage(), "the todoList list is empty !");
    }
    @Test
    public void should_TodoResponseDtoList_be_immutable(){
        //GIVEN
        List<Todo> todos = todoListForTest;

        //WHEN
        List<TodoResponseDto> todoResponseDtoList = todoMapper.toTodoResponseDtoList(todos);

        //THEN
        assertThrows(UnsupportedOperationException.class,() -> todoResponseDtoList.add(2,new TodoResponseDto("title8","description8",LocalDate.parse("22/05/1995"),LocalDate.parse("15/02/2023"),Priority.HIGH,true)));
    }



}
