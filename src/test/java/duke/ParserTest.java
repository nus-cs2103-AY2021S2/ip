package duke;

import duke.exception.InvalidDeadlineCommandException;
import duke.exception.InvalidEventCommandException;
import duke.exception.InvalidToDoCommandException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    TaskList userList = new TaskList();
    @Test
    public void testParseAddToDo() throws InvalidToDoCommandException {
        assertEquals("testing todo",
                new Parser().parseAddTodo("todo testing todo", userList).getTaskDetail());
        assertEquals("i want to do this!",
                new Parser().parseAddTodo("todo i want to do this!", userList).getTaskDetail());
        assertEquals("I WANT TO DO NOTHING",
                new Parser().parseAddTodo("todo I WANT TO DO NOTHING", userList).getTaskDetail());
    }

    @Test
    public void testParseAddEvent() throws InvalidEventCommandException {
        assertEquals("testing",
                new Parser().parseAddEvent("event testing /at 2020-01-01", userList).getTaskDetail());
        assertEquals(LocalDate.parse("2020-01-01"),
                new Parser().parseAddEvent("event test /at 2020-01-01", userList).getEventDate());
        assertEquals("testing event",
                new Parser().parseAddEvent("event testing event /at 2021-04-06", userList).getTaskDetail());
    }

    @Test
    public void testParseAddDeadline() throws InvalidDeadlineCommandException {
        assertEquals("testing",
                new Parser().parseAddDeadline("deadline testing /by 2020-01-01", userList).getTaskDetail());
        assertEquals(LocalDate.parse("2020-01-01"),
                new Parser().parseAddDeadline("deadline test /by 2020-01-01", userList).getDeadlineDate());
        assertEquals("testing 2",
                new Parser().parseAddDeadline("deadline testing 2 /by 2021-04-06", userList).getTaskDetail());
    }

}
