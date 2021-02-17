import static org.junit.jupiter.api.Assertions.assertEquals;

import datetime.KiwiDateTime;
import org.junit.jupiter.api.Test;
import tasklist.TaskList;
import tasks.Event;
import tasks.Todo;

public class TaskListTest {

    @Test
    public void taskListChecking() {
        TaskList t = new TaskList();
        t.add(new Todo("test todo 1"));
        t.add(new Todo("test todo 2"));
        t.add(new Event("test", KiwiDateTime.of(2,3,2021, 15,41)));
        t.deleteTask(2);
        assertEquals(t.size(), 2);
    }

}
