import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    void get() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Deadline("tutorial", "2020-01-01", true));
        tasks.add(new ToDo("homework"));
        tasks.add(new Event("concert", "2020-01-02", true));
        assertEquals(new Deadline("tutorial", "2020-01-01", true), new TaskList(tasks).get(0));
        assertEquals(new ToDo("homework"), new TaskList(tasks).get(1));
        assertEquals(new Event("concert", "2020-01-02", true), new TaskList(tasks).get(2));
    }

    @Test
    void size() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Deadline("tutorial", "2020-01-01", true));
        tasks.add(new ToDo("homework"));
        tasks.add(new Event("concert", "2020-01-02", true));
        assertEquals(3, tasks.size());
        tasks.add(new ToDo("something"));
        assertEquals(4, tasks.size());
    }
}
