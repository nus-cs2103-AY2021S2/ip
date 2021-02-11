package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class DeadlineTaskTest {

    @Test
    public void testEventGetType() {
        DeadlineTask task = new DeadlineTask("testing", LocalDate.of(2021, 12, 12));
        assertEquals("[D]", task.getType());
    }
}
