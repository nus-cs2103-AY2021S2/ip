package com.tanboonji.jhin;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.parser.DateParser;

class DateParserTest {

    @Test
    void parseDateTime_validDateTime_success() {
        String[] inputs = {"01/01/2021", "01/01/2021  0000", "01/01/2021  0000",
            "01.01.2021", "01.01.2021  0000", "01.01.2021  0000",
            "01-01-2021", "01-01-2021  0000", "01-01-2021  0000",
            "01012021", "01012021  0000", "01012021  0000",
            "01012021 00", "01/01/2021 00", "01-01-2021 00", "01.01.2021 00",
            "010121", "010121 00", "010121  0000",
            "", "0000", "2359", "12PM", "12:20AM", "12", "12:20",
            "010121 12AM", "010121 12:20AM", "010121 12", "010121 12:20"};
        for (String input: inputs) {
            try {
                DateParser.parseDateTime(input);
            } catch (Exception e) {
                fail();
            }
        }
    }

    @Test
    void testToString_validDateTime_success() {
        LocalDateTime dateTime = LocalDateTime.now();
        try {
            DateParser.toString(dateTime);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseDateTime_invalidDateTime_jhinExceptionThrown() {
        String[] inputs = {"01/13/2021", "01/01/  0000", "/01/2021  0000",
            "01..2021", "01.01.2021  -0000", "01.01.2021  2500",
            "01-01-20211", "101-01-2021  0000", "01-101-2021  0000"};
        for (String input: inputs) {
            try {
                DateParser.parseDateTime(input);
                fail();
            } catch (JhinException e) {
                // pass test case, no action required
            }
        }
    }
}
