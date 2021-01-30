package duke.parser;

import jdk.swing.interop.SwingInterOpUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeParserTest {
    private String INPUT = "01-02-2023 22:34";

    @Test
    void parse() {
        TimeParser timeParser = new TimeParser();
        System.out.println(timeParser.parse(INPUT));
        assertEquals("2023-02-01T22:34", timeParser.parse(INPUT).toString());
    }
}