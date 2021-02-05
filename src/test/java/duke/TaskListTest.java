package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

/**
 * Represents a test driver for <code>TaskList</code>.
 */
public class TaskListTest {

    /**
     * Tests the addTask() method.
     */
    @Test
    public void addTask_normalInput_addedCorrectly() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getSize());
        Todo tester = new Todo("read book", 0);
        taskList.addTask(tester);
        assertEquals(1, taskList.getSize());
        assertEquals(tester, taskList.getTask(1));
    }
}
