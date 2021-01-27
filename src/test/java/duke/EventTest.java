package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class EventTest {
    @Test
    public void initialisationTest() {
        Event event = new Event("CS2103T Tutorial",
                LocalDateTime.parse("28-01-2021 1200", Parser.INPUT_DATETIME_FORMAT));
        assertEquals("[E][ ] CS2103T Tutorial (at: 28 Jan 2021 12:00PM)", event.toString());
    }

    @Test
    public void markAsDoneTest() {
        Event event = new Event("CS2103T Tutorial",
                LocalDateTime.parse("28-01-2021 1200", Parser.INPUT_DATETIME_FORMAT));
        event.markAsDone();
        assertEquals("[E][X] CS2103T Tutorial (at: 28 Jan 2021 12:00PM)", event.toString());
    }

    @Test
    public void getStatusIconTest() {
        Event event = new Event("CS2103T Tutorial",
                LocalDateTime.parse("28-01-2021 1200", Parser.INPUT_DATETIME_FORMAT));
        assertEquals(" ", event.getStatusIcon());
    }
}
