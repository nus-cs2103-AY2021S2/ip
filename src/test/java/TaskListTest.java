import main.java.EmptyDescriptionException;
import main.java.TaskList;
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
            assertEquals("main.java.EmptyDescriptionException", e.toString());
        }
    }
}
