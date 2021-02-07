package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testFormattedText() {
        assertEquals("D | 0 | eat | 2020-10-10 18:00",
                new Deadline("eat", false, "2020-10-10 18:00").getFormattedData());
    }

    @Test
    public void testToString() {
        assertEquals("[D][] eat (by: Oct 10 2020 06:00PM)",
                new Deadline("eat", false, "2020-10-10 18:00").toString());
    }
}

