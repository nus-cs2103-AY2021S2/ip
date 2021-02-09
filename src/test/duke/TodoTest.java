import duke.Task;
import duke.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToString() {
        Task task = new Todo("Start yo homework boi");
        String actual = task.toString();
        String expected = "[T][✘] Start yo homework boi";
        assertEquals(actual, expected);
    }


}