import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {

    @Test
    public void event_wrongDateFormat_dukeExceptionThrown() {
        assertThrows(DukeException.class, () -> new Event("meeting", false, ""));
        assertThrows(DukeException.class, () -> new Event("meeting", true, "1 Jan 2020"));
        assertThrows(DukeException.class, () -> new Event("meeting", true, "01-01-2020"));
        assertThrows(DukeException.class, () -> new Event("meeting", false, "2020/01/01"));
    }

    @Test
    public void testSaveTask() throws DukeException {
        assertEquals("E | 1 | meeting | 2020-01-01",
                new Event("meeting", true, "2020-01-01").saveTask());
        assertEquals("E | 0 | meeting | 2020-01-01",
                new Event("meeting", false, "2020-01-01").saveTask());
    }

    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("[E][\u2713] meeting (At: 1 Jan 2020)",
                new Event("meeting", true, "2020-01-01").toString());
        assertEquals("[E][ ] meeting (At: 1 Jan 2020)",
                new Event("meeting", false, "2020-01-01").toString());
    }
}