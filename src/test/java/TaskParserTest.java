import duke.DukeException;
import duke.Task;
import duke.TaskParser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TaskParserTest {
    @Test
    public void todoTest() throws DukeException {
        Task task = TaskParser.parseTask("todo sample todo");
        String expectedParse = "[T][ ] sample todo";
        assertEquals(expectedParse, task.toString());
    }

    @Test
    public void todoTest_emptyDescription_exceptionThrown() {
        try {
            Task task = TaskParser.parseTask("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops! Usage: todo [desc]", e.getMessage());
        }
    }

    @Test
    public void eventTest() throws DukeException {
        Task task = TaskParser.parseTask("event sample event /on 20/11/2021");
        String expectedParse = "[E][ ] sample event (on: 20 Nov 2021)";
        assertEquals(expectedParse, task.toString());
    }

    @Test
    public void eventTest_emptyDate_exceptionThrown() {
        try {
            Task task = TaskParser.parseTask("event sample event /by 20/11/2021");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops! Usage: event [desc] /on [date]", e.getMessage());
        }
    }

    @Test
    public void eventTest_wrongDelimiter_exceptionThrown() {
        try {
            Task task = TaskParser.parseTask("event sample event /by 20/11/2021");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops! Usage: event [desc] /on [date]", e.getMessage());
        }
    }

    @Test
    public void eventTest_badDateFormat_exceptionThrown() {
        try {
            Task task = TaskParser.parseTask("event sample event /on 1/1/1970");
            fail();
        } catch (DukeException e) {
            assertEquals("Looks like your date's formatted incorrectly! Try this: dd/mm/yyyy", e.getMessage());
        }
    }

    @Test
    public void deadlineTest() throws DukeException {
        Task task = TaskParser.parseTask("deadline sample deadline /by 28/09/2021");
        String expectedParse = "[D][ ] sample deadline (by: 28 Sep 2021)";
        assertEquals(expectedParse, task.toString());
    }

    @Test
    public void deadlineTest_emptyDate_exceptionThrown() {
        try {
            Task task = TaskParser.parseTask("deadline sample deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops! Usage: deadline [desc] /by [date]", e.getMessage());
        }
    }

    @Test
    public void deadlineTest_wrongDelimiter_exceptionThrown() {
        try {
            Task task = TaskParser.parseTask("deadline sample deadline /on 28/09/2021");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops! Usage: deadline [desc] /by [date]", e.getMessage());
        }
    }

    @Test
    public void deadlineTest_badDateFormat_exceptionThrown() {
        try {
            Task task = TaskParser.parseTask("deadline sample deadline /by 1/1/1970");
            fail();
        } catch (DukeException e) {
            assertEquals("Looks like your date's formatted incorrectly! Try this: dd/mm/yyyy", e.getMessage());
        }
    }
}