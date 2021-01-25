package duke;

import org.junit.jupiter.api.Test;
import tasks.Deadline;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dummyTest() {
        Deadline deadline = new Deadline("hello", LocalDateTime.now());
        assertEquals("Deadline",deadline.getTaskType());
    }
}
