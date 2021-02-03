import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void taskListChecking() {
        TaskList t = new TaskList();
        t.add(new Todo("test todo 1"));
        t.add(new Todo("test todo 2"));
        t.add(new Event("finish ip level 9", "27-01 6AM"));
        t.remove(2);
        assertEquals(t.size(), 2);
    }

}
