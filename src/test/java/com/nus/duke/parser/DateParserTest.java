package com.nus.duke.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.nus.duke.common.DukeDateParserException;

class DateParserTest {

    public static final String[] VALID_DATE_TIME = {
            "Sunday", "Sunday 6pm", "Sunday 7PM", "Sunday 7 PM", "Sunday 7:15 PM",
            "Sunday 1900", "Sunday 2103", "13-12-2021", "13-12-2021 10 PM",
            "13-12-2021 10:13 PM", "13-12-2021 2103", "13/12/2021", "13/12/2021 10 PM",
            "13/12/2021 10:13 PM", "13/12/2021 2103", "13/12/2021 40323", "MoNdAy"
    };
    public static final String[] INVALID_DATE_TIME = {
            "Sundya", "Su", "12/14/2021"
    };

    @Test
    public void parseValidDateTime() {
        for (String dateString : VALID_DATE_TIME) {
            try {
                DateParser.parseDateTime(dateString);
            } catch (DukeDateParserException e) {
                fail();
            }
        }
    }

    @Test
    public void parseInvalidDateTimeAssertThrows() {
        for (String dateString : INVALID_DATE_TIME) {
            assertThrows(DukeDateParserException.class, () -> {
                DateParser.parseDateTime(dateString);
            });
        }
    }
}
