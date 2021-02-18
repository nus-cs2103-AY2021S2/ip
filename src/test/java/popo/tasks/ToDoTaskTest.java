package popo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTaskTest {
    private static final String TASK_NAME = "Read Book";

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] Read Book", new ToDoTask(TASK_NAME).toString());
        assertEquals("[T][ ] Read Book", new ToDoTask(TASK_NAME, false).toString());
        assertEquals("[T][X] Read Book", new ToDoTask(TASK_NAME, true).toString());
    }

    @Test
    public void getName_emptyString_success() {
        assertEquals("", new ToDoTask("").getName());
    }

    @Test
    public void getName_nonEmptyString_success() {
        assertEquals(TASK_NAME, new ToDoTask(TASK_NAME).getName());
    }

    @Test
    public void getTaskType_toDoTask_success() {
        assertEquals("T", new ToDoTask("").getTaskType());
    }

    @Test
    public void isDone_completedTask_true() {
        assertTrue(new ToDoTask(TASK_NAME, true).isDone());
    }

    @Test
    public void isDone_notCompletedTask_false() {
        assertFalse(new ToDoTask(TASK_NAME, false).isDone());
    }

    @Test
    public void completeTask_notCompletedTask_success() {
        ToDoTask toDoTask = new ToDoTask(TASK_NAME, false);
        toDoTask.completeTask();
        assertTrue(toDoTask.isDone());
    }
}
