import duke.EmptyDescriptionException;
import duke.TaskList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addTask_emptyDescription_exceptionThrown() {
        try {
            new TaskList().addTask("todo");
            fail();
        } catch (EmptyDescriptionException e) {
            assertEquals("duke.EmptyDescriptionException", e.toString());
        }
    }
}
