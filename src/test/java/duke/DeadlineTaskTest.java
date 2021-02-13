package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {

    @Test
    public void TimeTest() {
        DeadlineTask newTask = new DeadlineTask("deadline project meeting", "2019-12-01 4pm");
        assertEquals("2019-12-01 4pm", newTask.time);
    }

    @Test
    public void CorrectTimeConversionTest() {
        DeadlineTask newTask = new DeadlineTask("deadline project meeting", "2019-12-01 4pm");
        String convertedTime = newTask.convertDateTime();
        assertEquals("Sun, Dec 1 2019 4pm", convertedTime);
    }
}
