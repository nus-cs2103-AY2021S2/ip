import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Parser;
import duke.TaskList;
import duke.Task;
import duke.Ui;

class ParserTest {
    static TaskList tl;
    static Parser p;
    @BeforeAll
    static void setup() {
        tl = new TaskList();
        p = new Parser(tl, null);
    }

    @Test
    void test_parsing_delete_command() {
        tl.add(new Task("tester"));
        p.parseCommand("delete 1");
        assertEquals(0, tl.size());
    }

    @Test
    void test_parsing_event_command() {
        p.parseCommand("event test /at 4");
        assertEquals("[E][ ] test (at: 4)", tl.get(0).toString());
        tl.remove(0);
    }
}