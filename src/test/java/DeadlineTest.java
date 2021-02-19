import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Deadline;
import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] lab report (by: Jan 21 2021)",
                new Deadline("lab report", "2021-01-21").toString());
    }
}