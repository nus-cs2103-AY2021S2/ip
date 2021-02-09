import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ip.src.main.java.Event;

public class EventTest {
    @Test

    /*
      Tests the toString() method of the Event class to see if deadline object is represented as expected.
     */
    public void eventToStringTest() {
        Event event = new Event("Test" , "12/2/2019 18:00");
        String eventToString = event.toString();
        String expectedString = "E | 0 | " + "Test" + " | " + "12/02/2019 18:00";
        assertEquals(expectedString , eventToString);
    }
}
