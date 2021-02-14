package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import duke.tasks.Deadline;


public class DeadlineTest {
    @Test
    public void dummyTest() {
        Deadline deadline = new Deadline("hello", LocalDateTime.now());
        assertEquals("Deadline", deadline.getTaskType());
    }
}
