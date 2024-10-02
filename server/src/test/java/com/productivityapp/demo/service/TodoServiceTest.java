package com.productivityapp.demo.service;

import com.productivityapp.demo.domain.dto.TodoDto;
import com.productivityapp.demo.domain.dto.TodoResponseDto;
import com.productivityapp.demo.domain.entity.Todo;
import com.productivityapp.demo.domain.enumeration.Priority;
import com.productivityapp.demo.domain.mapper.TodoMapper;
import com.productivityapp.demo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
                "22/05/1998",
                "25/12/2025",
                Priority.MEDIUM,
                false
        );
        Todo todo = new Todo(
                0L,
                "title1",
                "description1",
                "22/05/1998",
                "25/12/2025",
                Priority.MEDIUM,
                false
        );
        Todo savedTodo = new Todo(
                0L,
                "title1",
                "description1",
                "22/05/1998",
                "25/12/2025",
                Priority.MEDIUM,
                false
        );
        TodoResponseDto todoResponseDto = new TodoResponseDto(
                "title1",
                "description1",
                "22/05/1998",
                "25/12/2025",
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
                new TodoResponseDto("title1", "description1", "20/12/2023", "15/02/2026", Priority.MEDIUM, true),
                new TodoResponseDto("title2", "description2", "20/12/2024", "01/12/2026", Priority.LOW, false)
        );
        List<Todo> todos = Arrays.asList(
                new Todo(0L, "title1", "description1", "20/12/2023", "15/02/2026", Priority.MEDIUM, true),
                new Todo(1L, "title2", "description2", "20/12/2024", "01/12/2026", Priority.LOW, false)
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
        Todo todoToSearch = new Todo(0L, "title1", "description1", "20/12/2023", "15/02/2026", Priority.MEDIUM, true);
        TodoResponseDto todoResponseDtoToSearch = new TodoResponseDto("title1", "description1", "20/12/2023", "15/02/2026", Priority.MEDIUM, true);

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

    @Test
    public void should_throw_EntityNotFoundException_when_todo_cannot_be_found_by_id() {
        //GIVEN
        Long todoId = 15L;

        //WHEN
        when(todoRepository.findById(15L)).thenReturn(Optional.empty());

        //THEN
        EntityNotFoundException message = assertThrows(EntityNotFoundException.class, () -> todoService.getTodoById(todoId));
        assertEquals(message.getMessage(), "there is no todo with this id");
    }

    @Test
    public void should_update_todo_when_we_search_by_todo_id_and_add_new_data(){
        //GIVEN
        Long todoId = 0L;
        Todo todoToSearchToFind = new Todo(0L, "title1", "description1", "20/12/2023", "15/02/2026", Priority.MEDIUM, true);
        TodoDto newTodoDtoData = new TodoDto("title1", "description1-example", "20/12/2010", "15/02/2026", Priority.LOW, false);
        TodoResponseDto expectedResponseDto = new TodoResponseDto("title1", "description1-example", "20/12/2010", "15/02/2026", Priority.LOW, false);

        //MOCK CALLS
        when(todoRepository.findById(todoId)).thenReturn(Optional.of(todoToSearchToFind));
        when(todoRepository.save(any(Todo.class))).thenAnswer(answer -> answer.getArgument(0));
        when(todoMapper.toTodoResponseDto(any(Todo.class))).thenReturn(expectedResponseDto);

        //WHEN
        TodoResponseDto responseDto = todoService.updateTodo(todoId,newTodoDtoData);

        //THEN
        assertEquals(expectedResponseDto.title(),responseDto.title());
        assertEquals(expectedResponseDto.description(),responseDto.description());
        assertEquals(expectedResponseDto.publishDate(),responseDto.publishDate());
        assertEquals(expectedResponseDto.deadLineDate(),responseDto.deadLineDate());
        assertEquals(expectedResponseDto.priority(),responseDto.priority());
        assertEquals(expectedResponseDto.isDone(),responseDto.isDone());

        verify(todoRepository, Mockito.times(1)).findById(todoId);
        verify(todoRepository, Mockito.times(1)).save(any(Todo.class));
        verify(todoMapper, Mockito.times(1)).toTodoResponseDto(any(Todo.class));
    }

    @Test
    public void should_throw_EntityNotFoundException_when_todo_cannot_be_found_by_id_method_updateTodo() {
        //GIVEN
        Long todoId = 15L;
        TodoDto newTodoDtoData = new TodoDto("title1", "description1-example", "20/12/2010", "15/02/2026", Priority.LOW, false);

        //WHEN
        when(todoRepository.findById(15L)).thenReturn(Optional.empty());

        //THEN
        EntityNotFoundException message = assertThrows(EntityNotFoundException.class, () -> todoService.updateTodo(todoId,newTodoDtoData));
        assertEquals(message.getMessage(), "there is no todo with this id");
    }

    @Test
    public void should_delete_a_todo_by_its_id(){
        //GIVEN
        Long todoId = 0L;
        Todo todoToSearchToFind = new Todo(0L, "title1", "description1", "20/12/2023", "15/02/2026", Priority.MEDIUM, true);

        //MOCK CALLS
        when(todoRepository.findById(todoId)).thenReturn(Optional.of(todoToSearchToFind));
        doNothing().when(todoRepository).delete(any(Todo.class));

        //WHEN
        todoService.deleteTodoById(todoId);

        //THEN
        verify(todoRepository,Mockito.times(1)).findById(todoId);
        verify(todoRepository,Mockito.times(1)).delete(any(Todo.class));
    }

}
