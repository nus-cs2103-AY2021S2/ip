package pason;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import pason.exceptions.PasonException;
import pason.storage.Storage;
import pason.tasks.TaskList;

public class TaskListTest {
    @Test
    public void doneTask_invalidIndex_exceptionThrown() throws Exception {
        try {
            TaskList taskList = new TaskList(new Storage());
            assertEquals("Good job! I've marked this task as done:\n[T][✓] new task", taskList.doneTask(1));
        } catch (PasonException e) {
            assertEquals("You've already marked this task as done.", e.getMessage());
        }
    }
}
