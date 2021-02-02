package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void todoTest() {
        try {
            Task task = Task.createTask("Read a book", "todo", "", "");
            assertEquals("T", task.getType());
            assertEquals("Read a book", task.getName());
            assertEquals("[T][ ] Read a book", task.toString());
            assertEquals("T | 0 | Read a book", task.toSaveFormat());
        } catch (DukeException ex) {
            assertEquals(0, 1); // force fail
        }
    }

    @Test
    public void eventTest() {
        try {
            Task task = Task.createTask("Eat a book", "event", "2019-09-16", "by");
            LocalDate date = LocalDate.parse("2019-09-16");
            assertEquals("E", task.getType());
            assertEquals("Eat a book", task.getName());
            assertEquals(date.toString(), task.getDate().toString());
            assertEquals("[E][ ] Eat a book (by: Sep 16 2019)", task.toString());
            assertEquals("E | 0 | Eat a book | 2019-09-16 | by", task.toSaveFormat());
        } catch (DukeException ex) {
            assertEquals(0, 1); // force fail
        }
    }

    @Test
    public void deadlineTest() {
        try {
            Task task = Task.createTask("Cook a book", "deadline", "2010-05-12", "in");
            LocalDate date = LocalDate.parse("2010-05-12");
            assertEquals("D", task.getType());
            assertEquals("Cook a book", task.getName());
            assertEquals(date.toString(), task.getDate().toString());
            assertEquals("[D][ ] Cook a book (in: May 12 2010)", task.toString());
            assertEquals("D | 0 | Cook a book | 2010-05-12 | in", task.toSaveFormat());
        } catch (DukeException ex) {
            assertEquals(0, 1); // force fail
        }
    }

    @Test
    public void parserTest() {
        Parser parser = new Parser("event Write a book /by 2020-09-16");
        assertEquals("event", parser.getRequest());
        assertEquals("Write a book /by 2020-09-16", parser.getArgs());
        try {
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
