package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToSavedString() {
        // test case 1
        Event evt1 = new Event("team meeting", "2020-10-15");
        assertEquals("E | 0 | team meeting | 2020-10-15", evt1.toSavedString());

        // test case 2
        Event evt2 = new Event("presentation", true, "2020-10-15");
        assertEquals("E | 1 | presentation | 2020-10-15", evt2.toSavedString());
    }
}
