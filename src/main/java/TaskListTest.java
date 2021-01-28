import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TasklistTest class is a test class for TaskList
 *
 * @version 28 Jan 2021
 * @author Zhang Peng
 */
class TaskListTest {
    ArrayList<Task> l = new ArrayList<Task>();
    Task t = new Task("todo borrow book");

    /**
     * This is the the test method for addToList method.
     * @return Nothing.
     */
    @Test
     void addToList() {
        new TaskList().addToList(l, t);
        assertEquals(1, l.size());

    }

    /**
     * This is the the test method for deleteFromList method.
     * @return Nothing.
     */
    @Test
    void deleteFromList() {
        new TaskList().addToList(l, t);
        new TaskList().deleteFromList(l,1);
        assertEquals(0, l.size());
    }
}