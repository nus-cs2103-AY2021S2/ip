package jeff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jeff.task.ToDo;

public class TaskListTest {
    @Test
    public void getNumTasksTest() {
        TaskList tl = new TaskList();
        tl.addTask(new ToDo("blah"));
        assertEquals(1, tl.getNumTasks());
    }
}
