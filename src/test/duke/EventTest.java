import duke.Event;
import duke.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testFormatToSave() {
        String EventDetails = "detailsOfTheEvent";
        Task task = new Event("xmas", EventDetails);
        String actual = task.toString();
        String expected = "[E][✘] xmas (at: detailsOfTheEvent)";
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        String EventDetails = "detailsOfTheEvent";
        Task task = new Event("xmas", EventDetails);
        String actual = task.toString();
        String expected = "[E][✘] xmas (at: detailsOfTheEvent)";
        assertEquals(expected, actual);
    }
}