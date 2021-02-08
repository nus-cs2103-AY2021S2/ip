package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DeadlineTest {
    private final String description;
    private final String dateTimeString;
    private final Deadline deadline;

    public DeadlineTest() {
        this.description = "CS2103 Quiz";
        this.dateTimeString = "2021-02-06 23:30";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(this.dateTimeString, formatter);
        this.deadline = new Deadline(this.description, dateTime);
    }

    @Test
    public void testDateTime() {
        assertEquals(this.dateTimeString, this.deadline.getByDateTimeString());
    }

    @Test
    public void testStatus() {
        assertEquals(this.getExpectedStatusString(false), this.deadline.getStatusString());
        this.deadline.markAsDone();
        assertEquals(this.getExpectedStatusString(true), this.deadline.getStatusString());
    }

    private String getExpectedStatusString(boolean isDone) {
        String statusSymbol = isDone ? "X" : " ";
        return "[D][" + statusSymbol + "] " + this.description + " (by: " + this.dateTimeString + ")";
    }
}
