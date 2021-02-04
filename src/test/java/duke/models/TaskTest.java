package duke.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testCreateDeadline() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 04, 04, 03, 00, 00);
        Deadline d = new Deadline("Deadline 1", dateTime);
        assertEquals("Deadline 1", d.getTaskName());
        assertEquals(dateTime, d.getDeadline());
    }

    @Test
    public void testCreateEvent() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 03, 04, 03, 00, 00);
        Event e = new Event("Event 1", dateTime);
        assertEquals("Event 1", e.getTaskName());
        assertEquals(dateTime, e.getDate());
    }

    @Test
    public void testMarkDone() {
        Todo t = new Todo("Todo 1");
        assertFalse(t.getTaskDone());
        t.markAsDone();
        assertTrue(t.getTaskDone());
    }
}
