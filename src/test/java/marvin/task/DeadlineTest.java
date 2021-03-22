package marvin.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testEncode() {
        Deadline deadline = new Deadline("deadline", "2020-12-12 1200");
        assertEquals("D/0/deadline/2020-12-12 1200", deadline.encode());
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("deadline", "2020-12-12 1200");
        assertEquals("[D][\u2718] deadline (by: Dec 12 2020 12:00 PM)", deadline.toString());
    }
}
