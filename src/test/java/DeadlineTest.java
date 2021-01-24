import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class DeadlineTest {

    @Test
    public void testDeadlineString() {
        assertEquals("[D][ ] read (by: Jan 28 2021)", new Deadline("read",
                LocalDate.parse("2021-01-28")).toString());
    }

    @Test
    public void testParseDate() {
        assertEquals("Jan 28 2021", new Deadline("read",
                LocalDate.parse("2021-01-28")).parseDate(LocalDate.parse("2021-01-28")));
    }

}
