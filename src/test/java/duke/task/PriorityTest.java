package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class PriorityTest {
    @Test
    void getValuePriority_correctInputs_success() {
        assertEquals(0, Priority.valueOf(0).getValue());
        assertEquals(1, Priority.valueOf(1).getValue());
        assertEquals(2, Priority.valueOf(2).getValue());
    }

    @Test
    void getValuePriority_outOfRangeInput_exceptionThrown() {
        try {
            assertEquals("", Priority.valueOf(3).getValue());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Error: Second argument out of range. Accepts only 0-2.", e.getMessage());
        }
    }

    @Test
    void toStringPriority_correctInputs_success() {
        assertEquals("!", Priority.valueOf(0).toString());
        assertEquals("!!", Priority.valueOf(1).toString());
        assertEquals("!!!", Priority.valueOf(2).toString());
    }
}
