package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    private final String description = "CS2103 Quiz";
    private final ToDo toDo = new ToDo(this.description);

    @Test
    public void testStatus() {
        assertEquals(this.getExpectedStatusString(false), this.toDo.getStatusString());
        this.toDo.markAsDone();
        assertEquals(this.getExpectedStatusString(true), this.toDo.getStatusString());
    }

    private String getExpectedStatusString(boolean isDone) {
        String statusSymbol = isDone ? "X" : " ";
        return "[T][" + statusSymbol + "] " + this.description;
    }
}
