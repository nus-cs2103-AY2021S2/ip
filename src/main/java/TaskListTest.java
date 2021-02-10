import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


/**
 * TasklistTest class is a test class for TaskList
 * @author Zhang Peng
 * @version 28 Jan 2021
 */
class TaskListTest {
    private final ArrayList<Task> l = new ArrayList<>();
    private final Task t = new Task("todo borrow book");

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
        new TaskList().deleteFromList(l, 1);
        assertEquals(0, l.size());
    }
}
