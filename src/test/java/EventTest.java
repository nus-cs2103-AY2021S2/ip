import ip.src.main.java.Event;
import ip.src.main.java.Duke;
import ip.src.main.java.Task;
import ip.src.main.java.ToDo;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    /**
     * Tests the toString() method of the Event class to see if deadline object is represented as expected.
     */
    public void eventToStringTest(){
        Event event = new Event("Test","12/2/2019 18:00");
        String eventToString = event.toString();
        String expectedString =  "E | 0 | " + "Test" + " | " + "12/02/2019 18:00";
        assertEquals(expectedString, eventToString);

    }
}