package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {

    @Test
    public void testEventGetType() {
        EventTask task = new EventTask("testing", "2020-01-01");
        assertEquals('E', task.getType());
    }
}
