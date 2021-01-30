package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void createEvent_tagOnly_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Event.createEvent(new String[]{"event"});
        });
    }

    @Test
    void createEvent_titleOnly_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Event.createEvent(new String[]{"event", "event title"});
        });
    }

    @Test
    void createEvent_dateTimeOnly_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Event.createEvent(new String[]{"event", "/at 01/01/2001 2359"});
        });
    }

    @Test
    void createEvent_allFields_success() throws DukeException {
        Task event = Event.createEvent(new String[]{"event", "event title /at 01/01/2001 2359"});
        assertEquals(event.getFormattedStorageString(), "EVENT /&/ 0 /&/ event title /&/ 2001-01-01T23:59\n");
    }
}