import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        assertEquals("[D][ ] example deadline task (05 Jan 2021)",
                new Deadline("example deadline task", LocalDate.parse("2021-01-05")).toString());
    }

    @Test
    public void getDataTest() {
        assertEquals("D!@#0!@#example deadline task!@#2021-01-05",
                new Deadline("example deadline task", LocalDate.parse("2021-01-05")).getData());
    }
}
