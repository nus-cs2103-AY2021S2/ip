import duke.Task;
import duke.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseAddTaskCommand_correctInput_success() {
        try {
            Task todo = Parser.parseAddTaskCommand("todo junit test");
            assertEquals(todo.toString(), "TODO[ ] junit test");

            Task event = Parser.parseAddTaskCommand("event junit test /at 2021-02-14");
            assertEquals(event.toFileString(), "EVNT[ ] junit test (at: 2021-02-14)");

            Task deadline = Parser.parseAddTaskCommand("deadline junit test /by 2021-02-14");
            assertEquals(deadline.toFileString(), "DDLN[ ] junit test (by: 2021-02-14)");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
