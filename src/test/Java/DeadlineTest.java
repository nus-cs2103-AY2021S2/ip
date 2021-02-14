import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class DeadlineTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy Hmm");

    @Test
    public void init_currentDateTime_success() {
        Deadline task = new Deadline("SampleTask", LocalDateTime.now());
        String expectedOutput = "[D] [ ] SampleTask (by: " + LocalDateTime.now()
                                        .format(formatter) + ")";
        assertEquals(expectedOutput, task.toString());
    }

    @Test
    public void format_currentDateTime_success() {
        Deadline task = new Deadline("SampleTask", LocalDateTime.now());
        assertEquals(LocalDateTime.now().format(formatter), task.format(LocalDateTime.now()));
    }
}
