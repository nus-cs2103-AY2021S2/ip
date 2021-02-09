package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
