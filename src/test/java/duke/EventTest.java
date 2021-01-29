package java.duke;

import duke.DukeEmptyDescriptionException;
import duke.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventTest(){
        try {
            assertEquals(new Event("event", "time").toString(), "[E]event (at: time)");
        } catch (DukeEmptyDescriptionException e) {

        }
    }
}