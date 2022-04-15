import duchess.Tasks.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D] [ ] Homework (by: Jan 31 2021)", new Deadline("Homework", "2021-01-31").toString());
    }

    @Test
    public void testGetDeadline() {
        Deadline d = new Deadline("Homework", "2021-01-31");
        assertEquals("2021-01-31", d.getDeadline());
    }

    @Test
    public void testGetFormattedDeadline() {
        Deadline d = new Deadline("Homework", "2021-01-31");
        assertEquals("Jan 31 2021", d.getFormattedDeadline());
    }

}
