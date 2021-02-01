package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Used to test Event, a subclass of Task.
 */
public class EventTest {
    @Test
    public void testName() {
        Event task = new Event("return book (at: 2020-05-01)");
        assertEquals("[E][ ] return book (at: May 01 2020)", task.toString());
    }

    @Test
    public void testMarkAsDone() {
        Event task = new Event("return book (at: 2020-05-01)");
        task.markAsDone();
        assertEquals("[E][X] return book (at: May 01 2020)", task.toString());
    }
}
