package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class DeadlineTest {
    @Test
    public void initialisationTest() {
        Deadline deadline = new Deadline("ip",
                LocalDateTime.parse("28-01-2021 2359", Parser.INPUT_DATETIME_FORMAT));
        assertEquals("[D][ ] ip (by: 28 Jan 2021 11:59PM)", deadline.toString());
    }

    @Test
    public void markAsDoneTest() {
        Deadline deadline = new Deadline("ip",
                LocalDateTime.parse("28-01-2021 2359", Parser.INPUT_DATETIME_FORMAT));
        deadline.markAsDone();
        assertEquals("[D][X] ip (by: 28 Jan 2021 11:59PM)", deadline.toString());
    }

    @Test
    public void getStatusIconTest() {
        Deadline deadline = new Deadline("ip",
                LocalDateTime.parse("28-01-2021 2359", Parser.INPUT_DATETIME_FORMAT));
        assertEquals(" ", deadline.getStatusIcon());
    }
}
