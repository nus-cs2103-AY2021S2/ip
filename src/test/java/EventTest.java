import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringCoversion() {
        assertEquals("[E][INPROGRESS] read book (at: May 2nd)",
                new Event("read book", "May 2nd").toString());
    }
}
