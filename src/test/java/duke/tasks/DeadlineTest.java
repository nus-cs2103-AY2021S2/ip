package duke.tasks;

import duke.exceptions.DukeException;
import duke.util.DateFormatter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests Deadline creation.
 */
public class DeadlineTest {
    @Test
    public void testDeadlineCreation() {
        LocalDate date = LocalDate.parse("1996-11-11");
        Task task1 = new Deadline("Eat dinner", date);
        assertEquals("[D][ ] Eat dinner (by: 11 Nov 1996)", task1.toString());
        Task task2 = new Deadline("Go to the gym", true, date);
        assertEquals("[D][X] Go to the gym (by: 11 Nov 1996)", task2.toString());
    }
}
