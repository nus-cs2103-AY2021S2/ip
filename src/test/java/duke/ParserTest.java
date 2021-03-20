package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.controller.Parser;
import duke.model.task.TaskList;

public class ParserTest {
    @Test
    public void commandExtractionTest() {
        assertEquals("deadline", new Parser("deadline return book /by Sunday").getCommand());
        assertEquals("event", new Parser("event team meeting /at 2:00pm").getCommand());
        assertEquals("todo", new Parser("todo submit ip jar").getCommand());
        assertEquals("list", new Parser("list").getCommand());
        assertEquals("error", new Parser("unknown").getCommand());
    }

    @Test
    public void checkPrintStringReturns() {
        Parser temp = new Parser("bye");
        assertEquals("Bye. Hope to see you again soon!", temp.print(new TaskList()));
    }
}
