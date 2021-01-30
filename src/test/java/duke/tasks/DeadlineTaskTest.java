package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {

    @Test
    public void testEventGetType() {
        DeadlineTask task = new DeadlineTask("testing", "2020-01-01");
        assertEquals('D', task.getType());
    }
}
