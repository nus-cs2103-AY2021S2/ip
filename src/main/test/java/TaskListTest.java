import Duke.Task;
import Duke.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addItemTest(){
        // Adding
        TaskList tasks = new TaskList();
        tasks.add(new Task("Sample task 1"));
        tasks.add(new Task("Sample task 2"));

        // Testing
        assertEquals("Sample task 1", tasks.get(0).toString());
        assertEquals("Sample task 2", tasks.get(1).toString());
        assertEquals(2, tasks.size());
    }
}
