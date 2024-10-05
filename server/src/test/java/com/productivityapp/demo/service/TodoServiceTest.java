package com.productivityapp.demo.service;

import com.productivityapp.demo.domain.dto.TodoDto;
import com.productivityapp.demo.domain.dto.TodoResponseDto;
import com.productivityapp.demo.domain.entity.Todo;
import com.productivityapp.demo.domain.enumeration.Priority;
import com.productivityapp.demo.domain.enumeration.TimeSpace;
import com.productivityapp.demo.domain.mapper.TodoMapper;
import com.productivityapp.demo.repository.TodoRepository;
import com.productivityapp.demo.util.DateUtil;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoMapper todoMapper;

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private AutoCloseable closeable;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void should_save_todo_when_we_post_a_todoDto() {
        //GIVEN
        TodoDto todoDto = new TodoDto(
                "title1",
                "description1",
                DateUtil.dateParser("22/05/1998"),
                DateUtil.dateParser("25/12/2025"),
                Priority.MEDIUM,
                false
        );
        Todo todo = new Todo(
                0L,
                "title1",
                "description1",
                DateUtil.dateParser("22/05/1998"),
                DateUtil.dateParser("25/12/2025"),
                Priority.MEDIUM,
                false
        );
        Todo savedTodo = new Todo(
                0L,
                "title1",
                "description1",
                DateUtil.dateParser("22/05/1998"),
                DateUtil.dateParser("25/12/2025"),
                Priority.MEDIUM,
                false
        );
        TodoResponseDto todoResponseDto = new TodoResponseDto(
                "title1",
                "description1",
                DateUtil.dateParser("22/05/1998"),
                DateUtil.dateParser("25/12/2025"),
                Priority.MEDIUM,
                false
        );


        //MOCK CALLS
        when(todoMapper.toTodo(todoDto)).thenReturn(todo);
        when(todoRepository.save(todo)).thenReturn(savedTodo);
        when(todoMapper.toTodoResponseDto(savedTodo)).thenReturn(todoResponseDto);


        //WHEN
        TodoResponseDto responseDto = todoService.saveTodo(todoDto);

        //THEN
        assertEquals(todoDto.title(), responseDto.title());
        assertEquals(todoDto.publishDate(), responseDto.publishDate());

        verify(todoMapper, Mockito.times(1)).toTodo(todoDto);
        verify(todoMapper, Mockito.times(1)).toTodoResponseDto(savedTodo);
        verify(todoRepository, Mockito.times(1)).save(todo);

    }

    @Test
    public void should_return_all_todoResponseDto() {
        //GIVEN
        List<TodoResponseDto> todoResponseDtoList = Arrays.asList(
                new TodoResponseDto("title1", "description1", DateUtil.dateParser("20/12/2023"), DateUtil.dateParser("15/02/2026"), Priority.MEDIUM, true),
                new TodoResponseDto("title2", "description2", DateUtil.dateParser("20/12/2024"), DateUtil.dateParser("01/12/2026"), Priority.LOW, false)
        );
        List<Todo> todos = Arrays.asList(
                new Todo(0L, "title1", "description1", DateUtil.dateParser("20/12/2023"), DateUtil.dateParser("15/02/2026"), Priority.MEDIUM, true),
                new Todo(1L, "title2", "description2", DateUtil.dateParser("20/12/2024"), DateUtil.dateParser("01/12/2026"), Priority.LOW, false)
        );
        //MOCK CALLS
        when(todoRepository.findAll()).thenReturn(todos);
        when(todoMapper.toTodoResponseDtoList(todos)).thenReturn(todoResponseDtoList);

        //WHEN
        List<TodoResponseDto> responseDtos = todoService.getAllTodos();

        //THEN
        assertEquals(todoResponseDtoList.size(), todos.size());
        verify(todoMapper, Mockito.times(1)).toTodoResponseDtoList(todos);
        verify(todoRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void should_return_one_todoResponseDto_by_its_id() {
        //GIVEN
        Long todoId = 0L;
        Todo todoToSearch = new Todo(0L, "title1", "description1", DateUtil.dateParser("20/12/2023"), DateUtil.dateParser("15/02/2026"), Priority.MEDIUM, true);
        TodoResponseDto todoResponseDtoToSearch = new TodoResponseDto("title1", "description1", DateUtil.dateParser("20/12/2023"), DateUtil.dateParser("15/02/2026"), Priority.MEDIUM, true);

        //MOCK CALLS
        when(todoRepository.findById(todoId)).thenReturn(Optional.of(todoToSearch));
        when(todoMapper.toTodoResponseDto(todoToSearch)).thenReturn(todoResponseDtoToSearch);

        //WHEN
        TodoResponseDto responseDto = todoService.getTodoById(todoId);

        //THEN
        assertEquals(responseDto.title(), todoToSearch.getTitle());
        assertEquals(responseDto.title(), todoResponseDtoToSearch.title());
        assertEquals(todoId, todoToSearch.getId());

        verify(todoRepository, Mockito.times(1)).findById(todoId);
        verify(todoMapper, Mockito.times(1)).toTodoResponseDto(todoToSearch);
    }

//    @Test
//    public void should_throw_EntityNotFoundException_when_todo_cannot_be_found_by_id() {
//        //GIVEN
//        Long todoId = 15L;
//
//        //WHEN
//        when(todoRepository.findById(15L)).thenReturn(Optional.empty());
//
//        //THEN
//        EntityNotFoundException message = assertThrows(EntityNotFoundException.class, () -> todoService.getTodoById(todoId));
//        assertEquals(message.getMessage(), "there is no todo with this id");
//    }

    @Test
    public void should_update_todo_when_we_search_by_todo_id_and_add_new_data() {
        //GIVEN
        Long todoId = 0L;
        Todo todoToSearchToFind = new Todo(0L, "title1", "description1", DateUtil.dateParser("20/12/2023"), DateUtil.dateParser("15/02/2026"), Priority.MEDIUM, true);
        TodoDto newTodoDtoData = new TodoDto("title1", "description1-example", DateUtil.dateParser("20/12/2010"), DateUtil.dateParser("15/02/2026"), Priority.LOW, false);
        TodoResponseDto expectedResponseDto = new TodoResponseDto("title1", "description1-example",DateUtil.dateParser("20/12/2010"), DateUtil.dateParser("15/02/2026"), Priority.LOW, false);

        //MOCK CALLS
        when(todoRepository.findById(todoId)).thenReturn(Optional.of(todoToSearchToFind));
        when(todoRepository.save(any(Todo.class))).thenAnswer(answer -> answer.getArgument(0));
        when(todoMapper.toTodoResponseDto(any(Todo.class))).thenReturn(expectedResponseDto);

        //WHEN
        TodoResponseDto responseDto = todoService.updateTodo(todoId, newTodoDtoData);

        //THEN
        assertEquals(expectedResponseDto.title(), responseDto.title());
        assertEquals(expectedResponseDto.description(), responseDto.description());
        assertEquals(expectedResponseDto.publishDate(), responseDto.publishDate());
        assertEquals(expectedResponseDto.deadLineDate(), responseDto.deadLineDate());
        assertEquals(expectedResponseDto.priority(), responseDto.priority());
        assertEquals(expectedResponseDto.isDone(), responseDto.isDone());

        verify(todoRepository, Mockito.times(1)).findById(todoId);
        verify(todoRepository, Mockito.times(1)).save(any(Todo.class));
        verify(todoMapper, Mockito.times(1)).toTodoResponseDto(any(Todo.class));
    }

    @Test
    public void should_throw_EntityNotFoundException_when_todo_cannot_be_found_by_id_method_updateTodo() {
        //GIVEN
        Long todoId = 15L;
        TodoDto newTodoDtoData = new TodoDto("title1", "description1-example", DateUtil.dateParser("20/12/2010"),DateUtil.dateParser("15/02/2026"), Priority.LOW, false);

        //WHEN
        when(todoRepository.findById(15L)).thenReturn(Optional.empty());

        //THEN
        EntityNotFoundException message = assertThrows(EntityNotFoundException.class, () -> todoService.updateTodo(todoId, newTodoDtoData));
        assertEquals(message.getMessage(), "there is no todo with this id");
    }

    @Test
    public void should_delete_a_todo_by_its_id() {
        //GIVEN
        Long todoId = 0L;
        Todo todoToSearchToFind = new Todo(0L, "title1", "description1", DateUtil.dateParser("20/12/2023"), DateUtil.dateParser("15/02/2026"), Priority.MEDIUM, true);

        //MOCK CALLS
        when(todoRepository.findById(todoId)).thenReturn(Optional.of(todoToSearchToFind));
        doNothing().when(todoRepository).delete(any(Todo.class));

        //WHEN
        todoService.deleteTodoById(todoId);

        //THEN
        verify(todoRepository, Mockito.times(1)).findById(todoId);
        verify(todoRepository, Mockito.times(1)).delete(any(Todo.class));
    }

    @Test
    public void should_throw_EntityNotFoundException_when_todo_cannot_be_found_by_id_method_deleteTodoById() {
        //GIVEN
        Long todoId = 10L;

        //WHEN
        when(todoRepository.findById(15L)).thenReturn(Optional.empty());

        //THEN
        EntityNotFoundException message = assertThrows(EntityNotFoundException.class, () -> todoService.deleteTodoById(todoId));
        assertEquals(message.getMessage(), "there is no todo with this id");
    }

    @Test
    public void should_return_todoResponseDtoList_for_the_future(){
        //GIVEN
        TimeSpace future = TimeSpace.FUTURE;
        LocalDate todayDate = LocalDate.now();
        List<Todo> todoList = Arrays.asList(
               new Todo( 0L, "title1", "description1", DateUtil.dateParser("20/12/2023"),DateUtil.dateParser("15/02/2026"), Priority.MEDIUM, true),
               new Todo( 1L, "title2", "description2", DateUtil.dateParser("20/12/2023"), DateUtil.dateParser("15/02/2025"), Priority.MEDIUM, false),
               new Todo( 2L, "title3", "description3", DateUtil.dateParser("20/12/2023"), DateUtil.dateParser("15/02/2028"), Priority.MEDIUM, true),
               new Todo( 3L, "title4", "description4", DateUtil.dateParser("20/12/2023"), DateUtil.dateParser("15/02/2035"), Priority.MEDIUM, true)
        );

        //MOCK CALLS
        when(todoRepository.findTodoByDeadLineDateAfter(todayDate)).thenReturn(todoList);

        //WHEN
        todoService.getAllTodosByTimeSpace(future);

        //THEN
        verify(todoRepository,Mockito.times(1)).findTodoByDeadLineDateAfter(todayDate);
        assertTrue(todoList.getFirst().getDeadLineDate().isAfter(todayDate));
    }

    @Test
    public void should_return_todoResponseDtoList_for_the_past(){
        //GIVEN
        TimeSpace past = TimeSpace.PAST;
        LocalDate todayDate = LocalDate.now();
        List<Todo> todoList = Arrays.asList(
                new Todo( 0L, "title1", "description1", DateUtil.dateParser("20/12/2023"),DateUtil.dateParser("15/02/1996"), Priority.MEDIUM, true),
                new Todo( 1L, "title2", "description2", DateUtil.dateParser("20/12/2023"), DateUtil.dateParser("15/02/2021"), Priority.MEDIUM, false),
                new Todo( 2L, "title3", "description3", DateUtil.dateParser("20/12/2023"), DateUtil.dateParser("15/02/2022"), Priority.MEDIUM, true),
                new Todo( 3L, "title4", "description4", DateUtil.dateParser("20/12/2023"), DateUtil.dateParser("15/02/1976"), Priority.MEDIUM, true)
        );

        //MOCK CALLS
        when(todoRepository.findTodoByDeadLineDateBefore(todayDate)).thenReturn(todoList);

        //WHEN
        todoService.getAllTodosByTimeSpace(past);

        //THEN
        verify(todoRepository,Mockito.times(1)).findTodoByDeadLineDateBefore(todayDate);
        assertTrue(todoList.getFirst().getDeadLineDate().isBefore(todayDate));
    }

    @Test
    public void should_return_todoResponseDtoList_for_today(){
        //GIVEN
        TimeSpace today = TimeSpace.PRESENT;
        LocalDate todayDate = LocalDate.now();
        List<Todo> todoList = Arrays.asList(
                new Todo( 0L, "title1", "description1", DateUtil.dateParser("20/12/2023"),LocalDate.now(), Priority.MEDIUM, true),
                new Todo( 1L, "title2", "description2", DateUtil.dateParser("20/12/2023"), LocalDate.now(), Priority.MEDIUM, false),
                new Todo( 2L, "title3", "description3", DateUtil.dateParser("20/12/2023"), LocalDate.now(), Priority.MEDIUM, true),
                new Todo( 3L, "title4", "description4", DateUtil.dateParser("20/12/2023"),LocalDate.now(), Priority.MEDIUM, true)
        );

        //MOCK CALLS
        when(todoRepository.findAllByDeadLineDate(todayDate)).thenReturn(todoList);

        //WHEN
        todoService.getAllTodosByTimeSpace(today);

        //THEN
        verify(todoRepository,Mockito.times(1)).findAllByDeadLineDate(todayDate);
        assertTrue(todoList.getFirst().getDeadLineDate().isEqual(todayDate));
    }



}
