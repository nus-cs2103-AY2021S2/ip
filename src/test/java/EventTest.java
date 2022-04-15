import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {
    @Test
    public void checkStrings() {
        String output = new Event("test", "NUS school").toString();
        String output1 = new Event("test", "NUS school").toSaveFormat();

        assertEquals("[E][ ] test (at: NUS school)", output);
        assertEquals("E|0|test|NUS school", output1);

    }

    @Test
    public void checkDoneStateChange() {
        Event event = new Event("testing at", "Now");
        event.changeTaskToDone();

        assertEquals("[E][X] testing at (at: Now)", event.toString());
        assertEquals("E|1|testing at|Now", event.toSaveFormat());
    }
}
