package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTaskTest {

    @Test
    public void testEventGetType() {
        ToDoTask task = new ToDoTask("testing");
        assertEquals('T', task.getType());
    }
}
