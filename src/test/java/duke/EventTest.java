package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    Event event = new Event("football competition", "2019-10-15");

    @Test
    public void getFormattedAtTest() {
        assertEquals("Oct 15 2019", event.getFormattedAt());
    }

    @Test
    public void getStatusIconTest() {
        assertEquals(" ", event.getStatusIcon());
    }
}
