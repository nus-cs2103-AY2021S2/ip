package duke.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void testCreateDeadline() {
        LocalDateTime datetime = LocalDateTime.of(2020, 04, 04, 03, 00, 00);
        Deadline d = new Deadline("Deadline 1", datetime);
        assertEquals("Deadline 1", d.getTaskName());
        assertEquals(datetime, d.getDeadline());
    }

    @Test
    public void testCreateEvent() {
        LocalDateTime datetime = LocalDateTime.of(2020, 03, 04, 03, 00, 00);
        Event e = new Event("Event 1", datetime);
        assertEquals("Event 1", e.getTaskName());
        assertEquals(datetime, e.getDate());
    }

    @Test
    public void testMarkDone() {
        Todo t = new Todo("Todo 1");
        assertFalse(t.getTaskDone());
        t.markAsDone();
        assertTrue(t.getTaskDone());
    }
}
