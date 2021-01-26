package java.duke;

import duke.DukeEmptyDescriptionException;
import duke.Task;
import duke.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void taskListTest() {
        TaskList tasklist = new TaskList();
        try {
            tasklist.add(new Task("task1"));
        } catch (DukeEmptyDescriptionException e) {

        }
        assertEquals(tasklist.size(), 1);
        tasklist.remove(0);
        assertEquals(tasklist.size(), 0);
    }
}