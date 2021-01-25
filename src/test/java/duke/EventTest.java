package duke;

import org.junit.jupiter.api.Test;
import tasks.Event;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void dummyTest() {
        Event event = new Event("hello", LocalDateTime.now());
        assertEquals("Event", event.getTaskType());

    }
}
