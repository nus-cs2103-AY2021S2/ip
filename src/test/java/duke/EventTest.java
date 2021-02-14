package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import duke.tasks.Event;

public class EventTest {
    @Test
    public void dummyTest() {
        Event event = new Event("hello", LocalDateTime.now());
        assertEquals("Event", event.getTaskType());

    }
}
