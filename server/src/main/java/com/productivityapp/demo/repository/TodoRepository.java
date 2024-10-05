package com.productivityapp.demo.repository;

import com.productivityapp.demo.domain.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findTodoByDeadLineDateAfter(LocalDate date);

    List<Todo> findTodoByDeadLineDateBefore(LocalDate date);

    List<Todo> findAllByDeadLineDate(LocalDate date);
}
