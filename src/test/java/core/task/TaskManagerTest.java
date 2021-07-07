package core.task;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {
    @Test
    public void addTaskTest() {
        Task t1 = new Todo("task1");
        Task t2 = new Deadline("task2 /by 2009-12-09");
        TaskManager tm = new TaskManager(false);
        tm.addTask(t1);
        tm.addTask(t2);
        ArrayList<Task> t = new ArrayList<>();
        t.add(t1);
        t.add(t2);
        assertEquals(true, tm.retrieveAllTasks().equals(t));
    }

    @Test
    public void savePathGetSetTest() {
        Path p = Paths.get("data");
        TaskManager tm = new TaskManager(false);
        tm.setSavePath(p);
        assertEquals(p, tm.getSavePath());
    }
}
