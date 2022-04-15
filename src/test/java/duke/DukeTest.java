package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2,2);
    }

    @Test
    public void eventCreation_properDate_success() {
        assertEquals(new Event("Eat Breakfast", "2020-01-01").toString(),
                "[E][ ] Eat Breakfast (at:Jan 1 2020 1200 AM)");
    }

    @Test
    public void eventCreation_invalidDate_exceptionThrown() {
        try {
            assertEquals("[E][ ] Eat Breakfast  (at:Jan 1 2020 1800 PM)",
                    new Event("Eat Breakfast", "tmr 6pm").toString());
            fail();
        } catch (Exception e) {
            assertEquals("Text 'tmr' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void taskList_emptyList_shouldBeEmpty() {
        assertEquals( true, new TaskList().isEmpty());
    }

}
