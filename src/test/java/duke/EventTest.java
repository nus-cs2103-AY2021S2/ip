package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {

    @Test
    public void testFormattedText() {
        assertEquals("E | 0 | eat | 2020-10-10 2-4pm",
                new Event("eat", false, "2020-10-10 2-4pm").getFormattedData());
    }

    @Test
    public void testToString() {
        assertEquals("[E][] eat (at: Oct 10 2020 2-4pm)",
                new Event("eat", false, "2020-10-10 2-4pm").toString());
    }
}
