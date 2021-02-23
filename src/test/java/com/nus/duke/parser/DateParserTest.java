package com.nus.duke.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.nus.duke.common.DukeDateTimeParserException;

class DateParserTest {

    public static final String[] VALID_DATE = {
            "Sunday", "sunday", "SUNDAY", "SuNdAy", "Sunday ", "   Sunday   ",
            "13-12-2021", "13/12/2021", "13122021", "12122021", "  13-12-2021  "
    };

    public static final String[] INVALID_DATE = {
            "somday", "wadnesday", "wed", "mon",
            "12-14-2021", "12/14/2021", "12142021", "14142021", "20211212"
    };

    public static final String[] VALID_TIME = {
            "10PM", "10pm", "10 PM", "10 pm",
            "10:59PM", "10:59pm", "10:59 PM", "10:59 pm",
            "1059PM", "1059pm", "1059 PM", "1059 pm",
            "2200", "22:00", "300", "0000"
    };
    public static final String[] INVALID_TIME = {
            "13PM", "13pm", "13 PM", "13 pm",
            "13:59tM", "13:59tm", "13:59 TM", "13:59 tm",
            "1359PM", "1359pm", "1359 PM", "1359 pm",
            "2260", "22:60"
    };

    @Test
    public void parseDateTime_validDateValidTime_parsedCorrectly() {
        Supplier<Stream<String>> validTimeStreamSupplier = () -> Arrays.stream(VALID_TIME);
        List<String> validDateTimeCombo = Arrays.stream(VALID_DATE)
                .flatMap(validDate ->
                        validTimeStreamSupplier.get().map(validTime -> validDate + " " + validTime))
                .collect(Collectors.toList());
        validDateTimeCombo.addAll(Arrays.asList(VALID_DATE));
        validDateTimeCombo.addAll(Arrays.asList(VALID_TIME));
        for (String dateString : validDateTimeCombo) {
            try {
                DateParser.parseDateTime(dateString);
            } catch (DukeDateTimeParserException e) {
                fail();
            }
        }
    }

    @Test
    public void parseDateTime_invalidDateValidTime_assertThrows() {
        Supplier<Stream<String>> timeStreamSupplier = () -> Arrays.stream(VALID_TIME);
        List<String> validDateTimeCombo = Arrays.stream(INVALID_DATE)
                .flatMap(validDate ->
                        timeStreamSupplier.get().map(validTime -> validDate + " " + validTime))
                .collect(Collectors.toList());
        for (String dateString : validDateTimeCombo) {
            assertThrows(DukeDateTimeParserException.class, () -> {
                DateParser.parseDateTime(dateString);
            });
        }
    }

    @Test
    public void parseDateTime_validDateInvalidTime_assertThrows() {
        Supplier<Stream<String>> timeStreamSupplier = () -> Arrays.stream(INVALID_TIME);
        List<String> validDateTimeCombo = Arrays.stream(VALID_DATE)
                .flatMap(validDate ->
                        timeStreamSupplier.get().map(validTime -> validDate + " " + validTime))
                .collect(Collectors.toList());
        for (String dateString : validDateTimeCombo) {
            assertThrows(DukeDateTimeParserException.class, () -> {
                DateParser.parseDateTime(dateString);
            });
        }
    }

    @Test
    public void parseDateTime_invalidDateInvalidTime_assertThrows() {
        Supplier<Stream<String>> timeStreamSupplier = () -> Arrays.stream(INVALID_TIME);
        List<String> validDateTimeCombo = Arrays.stream(INVALID_DATE)
                .flatMap(validDate ->
                        timeStreamSupplier.get().map(validTime -> validDate + " " + validTime))
                .collect(Collectors.toList());
        for (String dateString : validDateTimeCombo) {
            assertThrows(DukeDateTimeParserException.class, () -> {
                DateParser.parseDateTime(dateString);
            });
        }
    }

    @Test
    void hasOnlyTimeComponent_validTime_assertTrue() {
        for (String timeString : VALID_TIME) {
            assertTrue(DateParser.hasOnlyTimeComponent(timeString));
        }
    }

    @Test
    void hasOnlyTimeComponent_invalidTime_assertFalse() {
        for (String timeString : INVALID_TIME) {
            assertFalse(DateParser.hasOnlyTimeComponent(timeString));
        }
    }

    @Test
    void hasOnlyDateComponent_validDate_assertTrue() {
        for (String dateString : VALID_DATE) {
            assertTrue(DateParser.hasOnlyDateComponent(dateString));
        }
    }

    @Test
    void hasOnlyDateComponent_invalidDate_assertFalse() {
        for (String dateString : INVALID_TIME) {
            assertFalse(DateParser.hasOnlyDateComponent(dateString));
        }
    }
}
