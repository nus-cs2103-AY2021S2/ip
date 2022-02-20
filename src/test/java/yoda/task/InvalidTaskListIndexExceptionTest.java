package yoda.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class InvalidTaskListIndexExceptionTest {
    private TaskList tasks = new TaskList(new ArrayList<>());

    @Test
    void checkIfExceptionIsThrown() {
        ToDo todo = new ToDo("Test task");
        tasks.addTask(todo);
        assertThrows(InvalidTaskListIndexException.class, () -> tasks.accessTask(3));
    }

}
