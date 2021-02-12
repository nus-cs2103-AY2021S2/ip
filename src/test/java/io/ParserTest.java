package io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import project.io.Parser;

public class ParserTest {
    @Test
    public void parseIntParameterTest_validInt_success() {
        assertEquals(Parser.parseIntParameter("done 3"), 3);
    }

    @Test
    public void parseParameterTest_validExpression_success() {
        assertEquals(Parser.parseParameter("deadline task /by now", "/by", 1), "now");
    }

    @Test
    public void parseDeadlineParameterTest_validExpression_success() {
        String testDateTime = " 9090-01-01 00:00";
        LocalDateTime resultExpected = LocalDateTime.parse(testDateTime.trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(Parser.parseInputDateTime(testDateTime), resultExpected);
    }
}
