package duke.tasks;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TaskListTest {
    @Test
    public void addTest() {
        Task t1 = new Task("random1");
        Task t2 = new Task("random2");
        Task t3 = new Task("random3");
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
        TaskList taskList = new TaskList(tasks);
        assertEquals(taskList.get(0), t1);
        assertNotEquals(taskList.get(0), t3);
    }
}