package com.productivityapp.demo.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateUtil {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate dateParser(String dateStr) {

            return LocalDate.parse(dateStr, dateTimeFormatter);

    }

}
