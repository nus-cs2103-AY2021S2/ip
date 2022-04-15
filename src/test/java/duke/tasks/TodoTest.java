package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void initialisationTest() {
        Todo t = new Todo("random");
        assertEquals(t.isCompleted(), false);
    }

    @Test
    public void isDoneTest() {
        Todo t = new Todo("random");
        t = t.markAsDone();
        assertEquals(t.isCompleted(), true);
    }
}
