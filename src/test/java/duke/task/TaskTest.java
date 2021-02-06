package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void getDescriptionTest() {
        Task task = new Task("watch youtube");
        assertEquals("watch youtube", task.getDescription());
    }
}
