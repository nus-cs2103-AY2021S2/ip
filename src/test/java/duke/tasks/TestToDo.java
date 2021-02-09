package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TestToDo {
    private final String description = "CS2103 Quiz";
    private final ToDo toDo = new ToDo(this.description);

    /**
     * Initializes a <code>ToDo</code> instance with preset properties for testing.
     */
    @Test
    public void testStatus() {
        assertEquals(this.getExpectedStatusString(false), this.toDo.getStatusString());
        this.toDo.markAsDone();
        assertEquals(this.getExpectedStatusString(true), this.toDo.getStatusString());
    }

    /**
     * Builds an expected status string to be compared against the computed one.
     *
     * @param isDone Whether the task is done or not
     * @return The expected status string
     */
    private String getExpectedStatusString(boolean isDone) {
        String statusSymbol = isDone ? "X" : " ";
        return "[T][" + statusSymbol + "] " + this.description;
    }
}
