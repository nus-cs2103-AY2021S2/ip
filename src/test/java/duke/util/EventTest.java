package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void createEventTest() throws DukeInputException {
        assertThrows(DukeException.class, () -> Event.createEvent(""));
        assertThrows(DukeException.class, () -> Event.createEvent("a"));
        assertThrows(DukeException.class, () -> Event.createEvent("a /at"));
        assertThrows(DukeException.class, () -> Event.createEvent("a /at 1"));
        assertThrows(DukeException.class, () -> Event.createEvent("a 2011-01-01"));
        assertThrows(DukeException.class, () -> Event.createEvent("a/at2011-01-01"));

        Task t = Event.createEvent("testing /at 2011-01-01");
        assertEquals("[E][ ] testing (at: 1 Jan)", t.toString());
        t = t.markDone();
        assertEquals("[E][X] testing (at: 1 Jan)", t.toString());
    }
}
