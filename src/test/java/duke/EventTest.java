package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    Event event = new Event("football competition", "2019-10-15");

    @Test
    public void getFormattedAtTest() {
        assertEquals("Oct 15 2019", event.getFormattedDate());
    }

    @Test
    public void getStatusIconTest() {
        assertEquals(" ", event.getStatusIcon());
    }
}
