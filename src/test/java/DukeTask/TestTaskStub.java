package duketask;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTaskStub {

    @Test
    public void newTask_exceptionThrown () {
        Exception exception = assertThrows(TaskStub.EmptyDescriptionException.class, () -> {
            new TaskStub("", false, LocalDateTime.now());
        });

        assertTrue(exception.getMessage().contains("! Task description cannot be empty."));
    }

    @Test
    public void markAsDone () throws TaskStub.EmptyDescriptionException, TaskStub.MarkedAsDoneException {
        TaskStub taskStub = new TaskStub("testing task stub", false, LocalDateTime.now());
        taskStub.markAsDone();
        assertTrue(taskStub.isDone());
    }
}
