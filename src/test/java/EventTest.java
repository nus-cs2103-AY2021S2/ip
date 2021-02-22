import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void EventTesting() {
        Event event = new Event("marriage", "June 2020", false);
        assertEquals(event.toString(), "[E][ ] marriage (at: June 2020)");
        assertEquals(event.saveString(), "E|0|marriage|June 2020");
        assertEquals(event.finishTask().toString(), "[E][X] marriage (at: June 2020)");
    }
}
