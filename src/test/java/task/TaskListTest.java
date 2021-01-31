package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.ChecklstException;

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
        assertThrows(ChecklstException.class, () -> taskList.completeTask(-1));
        assertThrows(ChecklstException.class, () -> taskList.completeTask(0));
        assertThrows(ChecklstException.class, () -> taskList.completeTask(1));
    }

    @Test
    public void deleteTaskTest() throws ChecklstException {
        assertThrows(ChecklstException.class, () -> taskList.deleteTask(-1));
        assertThrows(ChecklstException.class, () -> taskList.deleteTask(0));
        assertThrows(ChecklstException.class, () -> taskList.deleteTask(1));
    }
    
}
