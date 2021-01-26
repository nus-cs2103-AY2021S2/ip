package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Event e = new Event("meeting friends", "1 Feb 21 1800");
        assertEquals("[E][ ] meeting friends (at: 01 Feb 2021, 6:00 PM)",
                e.toString());
    }
}
