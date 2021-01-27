package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

    @Test
    public void testEventGetType() {
        DeadlineTask task = new DeadlineTask("testing", LocalDate.of(2021, 12, 12));
        assertEquals("[D]", task.getType());
    }
}