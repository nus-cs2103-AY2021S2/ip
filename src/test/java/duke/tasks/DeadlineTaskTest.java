package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

    @Test
    public void testEventGetType() {
        DeadlineTask task = new DeadlineTask("testing", "2020-01-01");
        assertEquals('D', task.getType());
    }
}
