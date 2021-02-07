import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import tasklist.TaskList;

public class ParserTest {
    // public void testInvalidOnEmptyList() {

    @Test
    public void checkWrongArgumentsForTasks() {
        TaskList t = new TaskList();
        Parser p = new Parser(t);
        p.parseInputLine("todo hello");
        p.parseInputLine("todo");
        p.parseInputLine("deadline");
        p.parseInputLine("event");
        assertEquals(t.size(), 1);
    }
}
