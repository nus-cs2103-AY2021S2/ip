package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class EventFactoryTest {
    @Test
    void createEvent_emptyInput_exceptionThrown() {
        try {
            assertEquals("", new EventFactory().createTask("").toString());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Error: Description of event cannot be empty", e.getMessage());
        }
    }
    @Test
    void createEvent_wrongInput_exceptionThrown() {
        try {
            assertEquals("", new EventFactory().createTask("test").toString());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Error: Please add a start time to your event", e.getMessage());
        }
    }
    @Test
    void createEvent_correctInputs_success() {
        assertEquals("[E][ ][!!]testEvent (at:whenever)",
                new EventFactory().createTask("testEvent /at whenever").toString());
        assertEquals("[E][ ][!!]testEvent (at:1000hrs)",
                new EventFactory().createTask("testEvent /at 1000hrs").toString());
    }

    @Test
    void createEvent_correctInputsWithProperDates_success() {
        assertEquals("[E][ ][!!]testEvent (at:01 Jan 1998)",
                new EventFactory().createTask("testEvent /at 1998-01-01").toString());
        assertEquals("[E][ ][!!]testEvent (at:11 Dec 2019)",
                new EventFactory().createTask("testEvent /at 2019-12-11").toString());
    }
}
