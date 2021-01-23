package Duke.Helper;

import Duke.Exception.EmptyTaskException;
import Duke.Exception.InvalidTask;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParserTodo() throws EmptyTaskException {
        String[] input = new String[]{"todo brush the teeth", "todo read book", "todo return book"};
        Todo[] expectedOutput = new Todo[]{new Todo("brush the teeth"), new Todo("read book"),
                                            new Todo("return book")};
        for (int i = 0; i < input.length; i++) {
            Parser parser = new Parser();
            Todo output = parser.parseTodo(input[i]);
            Todo expected = expectedOutput[i];
            assertEquals(output.toString(), expected.toString());
        }
    }

    @Test
    public void testParserDeadline() throws EmptyTaskException, InvalidTask {
        String[] input  = new String[]{"deadline ip week 3 /by 2021-01-28 2359", "deadline assignment 1 /by 2021-02-01",
                                        "deadline math homework /by 2021-03-05"};
        Deadline[] expectedOutput = new Deadline[]{new Deadline("ip week 3", "Jan 28 2021 11:59pm"),
                                                    new Deadline("assignment 1", "Feb 1 2021"),
                                                    new Deadline("math homework", "Mar 5 2021")};
        for (int i = 0; i < input.length; i++) {
            Parser parser = new Parser();
            Deadline output = parser.parseDeadline(input[i]);
            Deadline expected = expectedOutput[i];
            assertEquals(output.toString(), expected.toString());
        }
    }

    @Test
    public void testParserEvent() throws EmptyTaskException, InvalidTask {
        String[] input  = new String[]{"event project meeting /at 2021-01-28 2359", "event VNC night /at 2021-02-01",
                                        "event night cycling /at 2021-03-05"};
        Event[] expectedOutput = new Event[]{new Event("project meeting", "Jan 28 2021 11:59pm"),
                new Event("VNC night", "Feb 1 2021"),
                new Event("night cycling", "Mar 5 2021")};
        for (int i = 0; i < input.length; i++) {
            Parser parser = new Parser();
            Event output = parser.parseEvent(input[i]);
            Event expected = expectedOutput[i];
            assertEquals(output.toString(), expected.toString());
        }
    }
}
