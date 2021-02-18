package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString() {
        LocalDateTime deadlineDateTime = LocalDateTime.of(2021, 01, 27, 23, 59);
        Deadline deadline = new Deadline("Homework 5", deadlineDateTime);
        assertEquals("[D][\u2718][0] Homework 5 (by: Jan 27 2021 23:59)", deadline.toString());
    }
    @Test
    public void testToFileString() {
        LocalDateTime deadlineDateTime = LocalDateTime.of(2021, 01, 27, 23, 59);
        Deadline deadline = new Deadline("Homework 5", deadlineDateTime);
        assertEquals("D | 0 | 0 | Homework 5 | 2021-01-27 2359", deadline.toFileString());
    }
}
