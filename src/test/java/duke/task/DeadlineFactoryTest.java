package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineFactoryTest {
    @Test
    void createDeadline_emptyInput_exceptionThrown() {
        try {
            assertEquals("", new DeadlineFactory().createTask("").toString());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Error: Description of deadline cannot be empty", e.getMessage());
        }
    }
    @Test
    void createDeadline_wrongInput_exceptionThrown() {
        try {
            assertEquals("", new DeadlineFactory().createTask("test").toString());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Error: Please add a time limit to your deadline", e.getMessage());
        }
    }
    @Test
    void createDeadline_correctInputs_success() {
        assertEquals("[D][ ][!!]testDeadline (by:whenever)",
                new DeadlineFactory().createTask("testDeadline /by whenever").toString());
        assertEquals("[D][ ][!!]testDeadline (by:1000hrs)",
                new DeadlineFactory().createTask("testDeadline /by 1000hrs").toString());
    }

    @Test
    void createDeadline_correctInputsWithProperDates_success() {
        assertEquals("[D][ ][!!]testEvent (by:01 Jan 1998)",
                new DeadlineFactory().createTask("testEvent /by 1998-01-01").toString());
        assertEquals("[D][ ][!!]testEvent (by:11 Dec 2019)",
                new DeadlineFactory().createTask("testEvent /by 2019-12-11").toString());
    }
}
