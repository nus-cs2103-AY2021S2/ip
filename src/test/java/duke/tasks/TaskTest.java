package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    private final String description = "CS2103 Quiz";
    private final Task task = new Task(this.description);

    @Test
    public void testDescription() {
        assertEquals(this.description, this.task.getDescription());
    }

    @Test
    public void testStatus() {
        assertFalse(this.task.isDone());
        assertEquals(this.getExpectedStatusString(false), this.task.getStatusString());

        this.task.markAsDone();
        assertTrue(this.task.isDone());
        assertEquals(this.getExpectedStatusString(true), this.task.getStatusString());
    }

    private String getExpectedStatusString(boolean isDone) {
        String statusSymbol = isDone ? "X" : " ";
        return "[" + statusSymbol + "] " + this.description;
    }
}
