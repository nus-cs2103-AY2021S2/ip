package com.tanboonji.duke.parser;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DateParserTest {

    @Test
    void parseDateTime_validDateTime_success() {
        String[] inputs = {"01/01/2021", " 01/01/2021  0000", "01/01/2021  0000 ",
            "01.01.2021", " 01.01.2021  0000", "01.01.2021  0000 ",
            "01-01-2021", " 01-01-2021  0000", "01-01-2021  0000 "};
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
    void parseDateTime_invalidDateTime_exceptionThrown() {
        String[] inputs = {"01/13/2021", " 01/01/  0000", "/01/2021  0000 ",
            "01..2021", " 01.01.2021  -0000", "01.01.2021  2500 ",
            "01-01-20211", " 101-01-2021  0000", "01-101-2021  0000 ",
            "01012021"};
        for (String input: inputs) {
            try {
                DateParser.parseDateTime(input);
                fail();
            } catch (Exception e) {
                // pass test case, no action required
            }
        }
    }
}
