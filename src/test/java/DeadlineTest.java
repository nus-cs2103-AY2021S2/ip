import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("test ", "2019-12-01");
        assertEquals("[D][ ] test (by: Dec 1 2019)", deadline.toString());
    }

    @Test
    public void saveTest() {
        Deadline deadline = new Deadline("test ", "2019-12-01");
        assertEquals("[D][ ] test (by: 2019-12-01)", deadline.save());
    }
}
