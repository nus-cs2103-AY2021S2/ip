import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {
    ArrayList<Task> l = new ArrayList<Task>();
    Task t = new Task("todo borrow book");

    @Test
     void addToList() {
        new TaskList().addToList(l, t);
        assertEquals(1, l.size());
    }

    @Test
    void deleteFromList() {
        new TaskList().addToList(l, t);
        new TaskList().deleteFromList(l,1);
        assertEquals(0, l.size());
    }
}