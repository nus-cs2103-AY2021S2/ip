import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import owen.OwenException;
import owen.task.DeadlineTask;

public class DeadlineTaskTest {
    @Test
    public void parseTask_correctFormat_success() throws OwenException {
        DeadlineTask task = new DeadlineTask("test /by 1/1/2021 1400");
        assertEquals("[D][ ] test (by: January 1 2021 2:00 PM)", task.toString());

        task = new DeadlineTask("test /by 01/01/2021 1400");
        assertEquals("[D][ ] test (by: January 1 2021 2:00 PM)", task.toString());
    }

    @Test
    public void parseTask_missingDateTime_exception() {
        try {
            new DeadlineTask("test");
            fail();
        } catch (OwenException exception) {
            assertEquals(
                    "Ooooo noo...\nDeadline task must have a description and due date/time...",
                    exception.getMessage());
        }
    }

    @Test
    public void parseTask_incorrectFormat_exception() {
        try {
            new DeadlineTask("test /by 2021-1-1 10pm");
            fail();
        } catch (OwenException exception) {
            assertEquals(
                    "Ooooo noo...\nDate and time must be in DD/MM/YYYY HHMM format...",
                    exception.getMessage());
        }
    }

    @Test
    public void markAsDone_markAsDone_success() throws OwenException {
        DeadlineTask task = new DeadlineTask("test /by 1/1/2021 1400");
        assertEquals("[D][ ] test (by: January 1 2021 2:00 PM)", task.toString());

        task = task.markAsDone();
        assertEquals("[D][X] test (by: January 1 2021 2:00 PM)", task.toString());
    }

    @Test
    public void serialize_serialize_success() throws OwenException {
        DeadlineTask task = new DeadlineTask("test /by 1/1/2021 1400");
        assertEquals("DEADLINE | false | test | 1/1/2021 1400", task.serialize());
    }

    @Test
    public void deserialize_correctFormat_success() throws OwenException {
        DeadlineTask task = DeadlineTask.deserialize("DEADLINE | false | test | 1/1/2021 1400");
        assertEquals("[D][ ] test (by: January 1 2021 2:00 PM)", task.toString());
    }

    @Test
    public void deserialize_incorrectFormat_exception() {
        try {
            DeadlineTask.deserialize("DEADLINE, false, test, 1/1/2021 1400");
            fail();
        } catch (Exception exception) {
            assertEquals("Index 1 out of bounds for length 1", exception.getMessage());
        }
    }
}
