package checklst.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import checklst.exception.ChecklstException;

public class TaskListTest {

    private final TaskList taskList = new TaskList();

    @Test
    public void addTaskTest() throws ChecklstException {
        Task task = new Todo("Hello");
        this.taskList.add(task);
        assertEquals(task, this.taskList.getTaskList().get(0));
    }

    @Test
    public void completeTaskTest() throws ChecklstException {
        assertThrows(ChecklstException.class, () -> this.taskList.completeTask(-1));
        assertThrows(ChecklstException.class, () -> this.taskList.completeTask(0));
        assertThrows(ChecklstException.class, () -> this.taskList.completeTask(1));
    }

    @Test
    public void deleteTaskTest() throws ChecklstException {
        assertThrows(ChecklstException.class, () -> this.taskList.deleteTask(-1));
        assertThrows(ChecklstException.class, () -> this.taskList.deleteTask(0));
        assertThrows(ChecklstException.class, () -> this.taskList.deleteTask(1));
    }

    @Test
    public void findTaskTest() throws ChecklstException {
        Task task = new Todo("hello");
        this.taskList.add(task);

        assertTrue(this.taskList.findTask("hello").getTaskList().size() > 0);
        assertThrows(ChecklstException.class, () -> this.taskList.findTask("abc"));
    }

}
