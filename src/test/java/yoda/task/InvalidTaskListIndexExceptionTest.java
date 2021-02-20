package yoda.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class InvalidTaskListIndexExceptionTest {
    private TaskList tasks = new TaskList(new ArrayList<>());

    @Test
    void checkIfExceptionIsThrown() throws InvalidTaskListIndexException {
        ToDo todo = new ToDo("Test task");
        tasks.addTask(todo);
        assertThrows(InvalidTaskListIndexException.class, () -> tasks.accessTask(3));
    }

}