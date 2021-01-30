import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class EventTest {
    @Test
    public void testInitialization() {
        Event event = new Event("description", LocalDateTime.parse("2021-01-01T00:00"));
        Assert.assertEquals("[E][ ] description (at: 01 Jan 2021, 00:00)", event.toString());

        Event event2 = new Event("description with space", LocalDateTime.parse("2021-01-01T00:00"));
        Assert.assertEquals("[E][ ] description with space (at: 01 Jan 2021, 00:00)", event2.toString());
    }

    @Test
    public void testDone() {
        Event event = new Event("description", LocalDateTime.parse("2021-01-01T00:00"));
        event.setDone();
        Assert.assertEquals("[E][X] description (at: 01 Jan 2021, 00:00)", event.toString());
    }
}
