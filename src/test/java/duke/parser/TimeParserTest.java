package duke.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class TimeParserTest {
    private String msg = "01-02-2023 22:34";

    @Test
    void parse() {
        TimeParser timeParser = new TimeParser();
        System.out.println(timeParser.parse(msg));
        assertEquals("2023-02-01T22:34", timeParser.parse(msg).toString());
    }
}
