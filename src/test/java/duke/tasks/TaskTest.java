package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void initialisationTest() {
        Task t = new Task("random");
        assertEquals(t.isCompleted(), false);
    }

    @Test
    public void isDoneTest() {
        Task t = new Task("random");
        t = t.markAsDone();
        assertEquals(t.isCompleted(), true);
    }
}
