package com.productivityapp.demo.domain.entity;

import com.productivityapp.demo.domain.enumeration.Priority;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 1,max = 50,message = "title has to contain 1 to 50 characters")
    private String title;

    @Size(max = 500,message = "the description has to contain 500 characters maximum")
    private String description;

    @NotNull
    private LocalDate publishDate;

    @NotNull(message = "dead line date must be filled this way : dd/mm/yyyy")
    private LocalDate deadLineDate;

    @NotNull(message = "there must be a priority")
    private Priority priority;

    @NotNull
    private boolean isDone;

}
