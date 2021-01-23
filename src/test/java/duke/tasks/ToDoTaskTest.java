package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {

    @Test
    public void testEventGetType() {
        ToDoTask task = new ToDoTask("testing");
        assertEquals('T', task.getType());
    }
}
