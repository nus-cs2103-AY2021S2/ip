import static org.junit.jupiter.api.Assertions.assertEquals;

import command.CommandFormatException;
import entry.Event;
import org.junit.jupiter.api.Test;

class EventTest {
    @Test
    void EventToString() throws CommandFormatException {
        Event e = new Event("eventDescription", "3 2 2021 1930");
        assertEquals("[E][ ] eventDescription (Wed 19:30, 3 FEBRUARY 2021)", e.toString());
    }

    @Test
    void EventToStorage() throws CommandFormatException {
        Event e = new Event("eventDescription", "3 2 2021 1930");
        assertEquals("D\u001EF\u001EeventDescription\u001E3 2 2021 1930", e.toStorage());
    }
}

