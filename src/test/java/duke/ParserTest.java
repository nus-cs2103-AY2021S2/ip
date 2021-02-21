package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parseTest() throws DukeException {
        assertEquals("todo finish english report ",
                Parser.parse("todo finish english report").getTaskDetails());
        assertEquals("list  ", Parser.parse("list").getTaskDetails());
        assertEquals("bye  ", Parser.parse("bye").getTaskDetails());
        assertEquals("done 5 ", Parser.parse("done 5").getTaskDetails());
        assertEquals("delete 3 ", Parser.parse("delete 3").getTaskDetails());
        assertEquals("deadline finish Duke Project /by Oct 10 2019 ",
                Parser.parse("deadline finish Duke Project /by Oct 10 2019").getTaskDetails());
        assertEquals("event project meeting /at Jan 27 2021 ",
                Parser.parse("event project meeting /at Jan 27 2021").getTaskDetails());

    }
}
