package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getDescriptionTest() {
        Task task = new Task("watch youtube");
        assertEquals("watch youtube", task.getDescription());
    }
}