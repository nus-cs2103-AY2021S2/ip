import duke.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        LocalDate parseDate = LocalDate.parse("2021-01-11");
        Deadline deadline = new Deadline("test", parseDate);
        assertEquals(deadline.toString(), "[D] [ ] test | by: 11 Jan 2021");
    }

    @Test
    public void getTimeTest() {
        LocalDate parseDate = LocalDate.parse("2021-01-11");
        Deadline deadline = new Deadline("test", parseDate);
        assertEquals(deadline.getTime(), parseDate);
    }
}
