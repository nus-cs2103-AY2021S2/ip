package pason;

import pason.exceptions.PasonException;
import pason.parser.Parser;
import org.junit.Test;
import pason.storage.Storage;
import pason.tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskListTest {
    @Test
    public void doneTask_invalidIndex_exceptionThrown() throws Exception {
        try {
            TaskList taskList = new TaskList(new Storage());
            assertEquals("Good job! I've marked this task as done:\n[T][✓] new task", taskList.doneTask(1));
            fail(); // the test should not reach this line
        } catch (PasonException e) {
            assertEquals("We couldn't find this task. Please enter the correct task number.", e.getMessage());
        }
    }
}
