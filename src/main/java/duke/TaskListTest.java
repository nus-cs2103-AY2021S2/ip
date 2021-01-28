package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a test driver for <code>TaskList</code>.
 */
public class TaskListTest {

    /**
     * Tests the addTask() method.
     */
    @Test
    public void TestAddTask() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getSize());
        Todo tester = new Todo("read book", 0);
        taskList.addTask(tester);
        assertEquals(1, taskList.getSize());
        assertEquals(tester, taskList.getTask(1));
    }
}
