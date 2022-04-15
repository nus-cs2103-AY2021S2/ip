package dukebody;

import duketask.Task;
import duketask.Event;
import duketask.TaskStub;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestParser {

    @Test
    public void taskToCommandToTask () throws Event.EmptyDescriptionException,
            Duke.UnrecognisedCommandException {

        Event event = new Event("testing event", true, LocalDateTime.now(),
                LocalDateTime.now());

        String taskCommand = Parser.taskToCommand(event);
        Task reconstructedTask = Parser.commandToTask(taskCommand);

        assertEquals(Parser.taskToCommand(reconstructedTask),
                Parser.taskToCommand(event));
    }

    public void taskToCommandToTask_exceptionThrown () throws Event.EmptyDescriptionException {
        TaskStub taskStub = new TaskStub("testing task stub", true, LocalDateTime.now());
        String taskCommand = Parser.taskToCommand(taskStub);

        assertThrows(Duke.UnrecognisedCommandException.class, () -> {
            Parser.commandToTask(taskCommand);
        });
    }
}
