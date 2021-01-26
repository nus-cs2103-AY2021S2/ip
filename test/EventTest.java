import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Event event = new Event("project meeting", LocalDate.parse("2021-03-03"));
        assertEquals("[E][ ]  project meeting (at: 03/03/2021)", event.toString());
    }
}