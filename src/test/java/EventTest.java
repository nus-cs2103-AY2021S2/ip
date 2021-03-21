import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duck.task.Event;

public class EventTest {
    private Event event = new Event("return book", "2021-12-22");

    @Test
    public void dummyTest() {
        assertEquals("E | 0 | return book | 2021-12-22", event.getTaskInfoOfFile());
        assertEquals("[\u2718]", event.getStatusIcon());
    }
}
