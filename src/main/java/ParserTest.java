import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A class that conducts JUnit test on the methods from Parser class.
 */
class ParserTest {
    /**
     * A test for the parse() method in Parse class.
     */
    @Test
    void parse() throws IOException, DukeException {
        Parser.parse("todo borrow book");
        Parser.parse("event go project meeting /at 1/2/2019 1800");
        Parser.parse("deadline return book /by 12/1/2018 1730");
        assertEquals(new ToDo("borrow book").toString(), TaskList.tasks.get(0).toString());
        assertEquals(new Event("go project meeting", LocalDate.parse("2019-02-01"),
        LocalTime.parse("18:00"), LocalTime.parse("19:00")).toString(), TaskList.tasks.get(1).toString());
        assertEquals(new Deadline("return book", LocalDate.parse("2018-01-12"),
                LocalTime.parse("17:30")).toString(), TaskList.tasks.get(2).toString());
    }

    /**
     * A test for the format() method in Parse class.
     */
    @Test
    void format() {
        assertEquals("01/02/2001", Parser.format("1/2/2001"));
        assertEquals("11/12/2001", Parser.format("11/12/2001"));
        assertEquals("11/02/2001", Parser.format("11/2/2001"));
        assertEquals("01/12/2001", Parser.format("1/12/2001"));
    }
}
