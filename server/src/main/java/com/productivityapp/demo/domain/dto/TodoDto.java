package com.productivityapp.demo.domain.dto;

import com.productivityapp.demo.domain.enumeration.Priority;

public record TodoDto(

        String title,

        String description,

        String publishDate,

        String deadLineDate,

        Priority priority,

        boolean isDone
) {
}
