import duke.Task;
import duke.Todo;
import duke.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {

    @Test
    public void testMarKAsDone() {
        assertEquals(true, new Todo("bring water").markAsDone());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] project (by: Jan 21 2020)", 
                new Deadline("project", "2020-01-21").toString());
    }
}
