package duke.datevalidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateValidatorLocalDateTest {
    private DateValidatorLocalDate validator;

    @BeforeEach
    public void setUp() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d HHmm", Locale.ENGLISH);
        validator = new DateValidatorLocalDate(formatter);
    }

    @Test
    @DisplayName("Dates without / as separators should not work")
    public void testWrongSeparator() {
        assertFalse(validator.isValid("20210101 2359"));
    }

    @Test
    @DisplayName("Inputs without time should not work")
    public void testNoTime() {
        assertFalse(validator.isValid("2021/01/01"));
    }

    @Test
    @DisplayName("Correct inputs should work")
    public void testCorrectInput() {
        assertTrue(validator.isValid("2020/01/1 2359"));
    }
}
