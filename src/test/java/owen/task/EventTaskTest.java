import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import owen.OwenException;
import owen.task.EventTask;

public class EventTaskTest {
    @Test
    public void parseTask_correctFormat_success() throws OwenException {
        EventTask task = new EventTask("test /at 1/1/2021 1400 - 01/01/2021 1600");
        assertEquals(
                "[E][ ] test (at: January 1 2021 2:00 PM - January 1 2021 4:00 PM)",
                task.toString());
    }

    @Test
    public void parseTask_missingDateTime_exception() {
        try {
            new EventTask("test");
            fail();
        } catch (OwenException exception) {
            assertEquals(
                    "Ooooo noo...\nEvent task must have a description and start/end time...",
                    exception.getMessage());
        }

        try {
            new EventTask("test /at 1/1/2021 1400");
            fail();
        } catch (OwenException exception) {
            assertEquals(
                    "Ooooo noo...\nBoth start and end date/time must be specified...",
                    exception.getMessage());
        }
    }

    @Test
    public void parseTask_incorrectFormat_exception() {
        try {
            new EventTask("test /at 2021-1-1 8pm - 2021-1-1 10pm");
            fail();
        } catch (OwenException exception) {
            assertEquals(
                    "Ooooo noo...\nDate and time must be in DD/MM/YYYY HHMM format...",
                    exception.getMessage());
        }
    }

    @Test
    public void markAsDone_markAsDone_success() throws OwenException {
        EventTask task = new EventTask("test /at 1/1/2021 1400 - 01/01/2021 1600");
        assertEquals(
                "[E][ ] test (at: January 1 2021 2:00 PM - January 1 2021 4:00 PM)",
                task.toString());

        task = task.markAsDone();
        assertEquals(
                "[E][X] test (at: January 1 2021 2:00 PM - January 1 2021 4:00 PM)",
                task.toString());
    }

    @Test
    public void serialize_serialize_success() throws OwenException {
        EventTask task = new EventTask("test /at 1/1/2021 1400 - 01/01/2021 1600");
        assertEquals("EVENT | false | test | 1/1/2021 1400 | 1/1/2021 1600", task.serialize());
    }

    @Test
    public void deserialize_correctFormat_success() throws OwenException {
        EventTask task = EventTask.deserialize(
                "EVENT | false | test | 1/1/2021 1400 | 1/1/2021 1600");
        assertEquals(
                "[E][ ] test (at: January 1 2021 2:00 PM - January 1 2021 4:00 PM)",
                task.toString());
    }

    @Test
    public void deserialize_incorrectFormat_exception() {
        try {
            EventTask.deserialize("EVENT, false, test, 1/1/2021 1400, 1/1/2021 1600");
            fail();
        } catch (Exception exception) {
            assertEquals("Index 1 out of bounds for length 1", exception.getMessage());
        }
    }
}
