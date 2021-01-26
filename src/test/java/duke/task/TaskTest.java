package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    void setDone_testTask_false() {
        Task t = new Task("test");
        t.setDone(false);
        assertFalse(t.isDone());
    }

    @Test
    void setDone_testTask_true() {
        Task t = new Task("test");
        t.setDone(true);
        assertTrue(t.isDone());
    }
}
