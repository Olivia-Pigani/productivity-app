package com.productivityapp.demo.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilTest {

    @Test
    public void should_turn_date_String_format_to_LocalDate_format() {
        //GIVEN
        String dateStr = "10/02/1996";
        LocalDate dateStrLD = LocalDate.of(1996, 02, 10);

        //WHEN
        LocalDate resultDateLD = DateUtil.dateParser(dateStr);

        //THEN
        assertEquals(resultDateLD, dateStrLD);
    }


}
