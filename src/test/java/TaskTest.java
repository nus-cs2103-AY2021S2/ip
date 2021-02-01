import ip.src.main.java.Duke;
import ip.src.main.java.Task;
import ip.src.main.java.ToDo;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    /**
     * Tests if the markdone() method in the Task class behaves as expected.
     */
    public void taskDoneTest(){
        Task task = new Task("Test");
        task.markDone();
        assertTrue(task.done == true);

    }
}
