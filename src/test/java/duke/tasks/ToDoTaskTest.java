package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTaskTest {
    private static final String taskName = "Read Book";
    
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] Read Book", new ToDoTask(taskName).toString());
        assertEquals("[T][ ] Read Book", new ToDoTask(taskName, false).toString());
        assertEquals("[T][X] Read Book", new ToDoTask(taskName, true).toString());
    }
    
    @Test
    public void getName_emptyString_success() {
        assertEquals("", new ToDoTask("").getName());
    }
    
    @Test
    public void getName_nonEmptyString_success() {
        assertEquals(taskName, new ToDoTask(taskName).getName());
    }

    @Test
    public void getTaskType_toDoTask_success() {
        assertEquals("T", new ToDoTask("").getTaskType());
    }

    @Test
    public void isDone_completedTask_success() {
        assertTrue(new ToDoTask(taskName, true).isDone());
    }

    @Test
    public void isDone_notCompletedTask_success() {
        assertFalse(new ToDoTask(taskName, false).isDone());
    }

    @Test
    public void completeTask_notCompletedTask_success() {
        ToDoTask toDoTask = new ToDoTask(taskName, false);
        toDoTask.completeTask();
        assertTrue(toDoTask.isDone());
    }
}
