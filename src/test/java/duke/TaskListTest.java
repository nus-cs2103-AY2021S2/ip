package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    void deleteTask_emptyIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        try {
            tasks.addTask("todo", new String[]{"Homework", ""});
            tasks.deleteTask("");
            fail();
        } catch (Exception e) {
            assertEquals("I need a task number...", e.getMessage());
        }
    }

    @Test
    void deleteTask_emptyList_exceptionThrown() {
        TaskList tasks = new TaskList();
        try {
            tasks.deleteTask("1");
            fail();
        } catch (Exception e) {
            assertEquals("I don't think there is such a task...", e.getMessage());
        }
    }

    @Test
    void deleteTask_invalidTask_exceptionThrown() {
        TaskList tasks = new TaskList();
        try {
            tasks.addTask("todo", new String[]{"Homework", ""});
            tasks.deleteTask("99");
            fail();
        } catch (Exception e) {
            assertEquals("I don't think there is such a task...", e.getMessage());
        }
    }
}
