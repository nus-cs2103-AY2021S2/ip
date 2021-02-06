package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTest {
    private final String description;
    private final String dateTimeString;
    private final Event event;

    public EventTest() {
        this.description = "CS2103 Quiz";
        this.dateTimeString = "2021-02-06 23:30";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(this.dateTimeString, formatter);
        this.event = new Event(this.description, dateTime);
    }

    @Test
    public void testDateTime() {
        assertEquals(this.dateTimeString, this.event.getAtDateTimeString());
    }

    @Test
    public void testStatus() {
        assertEquals(this.getExpectedStatusString(false), this.event.getStatusString());
        this.event.markAsDone();
        assertEquals(this.getExpectedStatusString(true), this.event.getStatusString());
    }

    private String getExpectedStatusString(boolean isDone) {
        String statusSymbol = isDone ? "X" : " ";
        return "[E][" + statusSymbol + "] " + this.description + " (at: " + this.dateTimeString + ")";
    }
}
