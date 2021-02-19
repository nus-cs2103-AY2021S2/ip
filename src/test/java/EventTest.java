import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] fieldtrip (at: Feb 17 2021)",
                new Event("fieldtrip", "2021-02-17").toString());
    }

    @Test
    public void testMarkDone() {
        assertEquals(true,
                new Event("sampling", "2021-03-10").markAsDone());
    }
}
