package com.productivityapp.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.productivityapp.demo.domain.enumeration.Priority;

import java.time.LocalDate;

public record TodoResponseDto(

        String title,

        String description,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate publishDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate deadLineDate,

        Priority priority,

        boolean isDone
) {
}
