package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for task list class.
 */
public class TaskListTest {

    @Test
    public void testAddTask() throws DukeNumOutOfRangeException {
        TaskList tl = new TaskList();
        Task t1 = new ToDo("read book");
        tl.add(t1);
        Task t2 = tl.get(0);
        assertEquals(t1.getName(), t2.getName());
    }

    @Test
    public void testDeleteTask() throws DukeNumOutOfRangeException {
        TaskList tl = new TaskList();
        Task t1 = new ToDo("read book");
        tl.add(t1);
        Task t2 = tl.remove(0);
        assertEquals(t1.getName() , t2.getName());
    }
}
