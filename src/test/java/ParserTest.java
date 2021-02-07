import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.TaskList;
import duke.task.Task;

class ParserTest {
    private static TaskList tl;
    private static Parser p;
    @BeforeAll
    static void setup() {
        tl = new TaskList();
        p = new Parser(tl, null);
    }

    @Test
    void testParsingDeleteCommand() {
        tl.add(new Task("tester"));
        p.parseCommand("delete 1");
        assertEquals(0, tl.size());
    }

    @Test
    void testParsingEventCommand() {
        p.parseCommand("event test /at 4");
        assertEquals("[E][ ] test (at: 4)", tl.get(0).toString());
        tl.remove(0);
    }
}
