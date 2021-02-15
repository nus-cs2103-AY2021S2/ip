package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void todoTest() {
        Task task = new Todo("Read a book");
        assertEquals("Read a book", task.getName());
        assertEquals("[T][ ] Read a book", task.toString());
        assertEquals("T | 0 | Read a book", task.toSaveFormat());

    }

    @Test
    public void eventTest() {
        try {
            Event event = new Event("Eat a book", "2019-09-16", "by");
            LocalDate date = LocalDate.parse("2019-09-16");
            assertEquals("Eat a book", event.getName());
            assertEquals(date.toString(), event.getDate().toString());
            assertEquals("[E][ ] Eat a book (by: Sep 16 2019)", event.toString());
            assertEquals("E | 0 | Eat a book | 2019-09-16 | by", event.toSaveFormat());
        } catch (DukeException ex) {
            assertEquals(1, 0);
        }
    }

    @Test
    public void deadlineTest() {
        try {
            Deadline deadline = new Deadline("Cook a book", "2010-05-12", "in");
            LocalDate date = LocalDate.parse("2010-05-12");
            assertEquals("Cook a book", deadline.getName());
            assertEquals(date.toString(), deadline.getDate().toString());
            assertEquals("[D][ ] Cook a book (in: May 12 2010)", deadline.toString());
            assertEquals("D | 0 | Cook a book | 2010-05-12 | in", deadline.toSaveFormat());
        } catch (DukeException ex) {
            assertEquals(1, 0);
        }
    }

    @Test
    public void parserTest() {
        try {
            Parser parser = new Parser("event Write a book /by 2020-09-16");
            assertEquals("event", parser.getRequest());
            assertEquals("Write a book /by 2020-09-16", parser.getArgs());
            String taskName = parser.getFormattedCommand()[0];
            String date = parser.getFormattedCommand()[1];
            String preposition = parser.getFormattedCommand()[2];
            assertEquals("Write a book", taskName);
            assertEquals("2020-09-16", date);
            assertEquals("by", preposition);
        } catch (DukeException ex) {
            assertEquals(0, 1); // force fail
        }
    }
}
