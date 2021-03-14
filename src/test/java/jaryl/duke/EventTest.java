package jaryl.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        try {
            Event event = new Event("Jay Chou Concert", "02/12/2021 1800");
            assertEquals(event.toString(), "[E][ ] Jay Chou Concert (at: 02 Dec 2021 1800)");
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
