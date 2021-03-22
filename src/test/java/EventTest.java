import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    public EventTest() {

    }

    @Test
    public void parse_emptyInput_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Event.parse("");
        });

        String expected = "Event description cannot be empty";
        String actual = exception.getMessage();
        assertEquals(actual, expected);
    }

}
