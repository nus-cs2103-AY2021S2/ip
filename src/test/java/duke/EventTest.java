package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventTest(){
        try {
            assertEquals(new Event("event", "time").toString(), "[E][ ] event (at: time)");
        } catch (DukeEmptyDescriptionException e) {

        }
    }
}
