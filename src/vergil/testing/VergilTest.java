package vergil.testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import vergil.components.TaskList;
import vergil.types.Todo;
import vergil.types.VergilException;

public class VergilTest {
    TaskList taskList = new TaskList();

    @Test
    public void addTodo_emptyTaskList_taskListWithAnIncompleteTodoTask() {
        taskList.add(new Todo("A to-do task"));

        assertEquals(
                "1. [T][ ] A to-do task\n",
                taskList.toString());
    }

    @Test
    public void completeTodo_emptyTaskList_taskListWithACompletedTodoTask() {
        taskList.add(new Todo("A to-do task"));

        try {
            taskList.complete(1);
        } catch (VergilException e) {
            // Do nothing.
        } finally {
            assertEquals(
                    "1. [T][X] A to-do task\n",
                    taskList.toString());
        }
    }
}
