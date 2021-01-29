package DukeBody;

import DukeTask.Task;
import DukeTask.Event;
import DukeTask.TaskStub;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestParser {

    @Test
    public void taskToCommandToTask () throws Event.EmptyDescriptionException,
            Parser.UnrecognisedCommandException {

        Event event = new Event("testing event", true, LocalDateTime.now(),
                LocalDateTime.now());

        String taskCommand = Parser.taskToCommand(event);
        Task reconstructedTask = Parser.commandToTask(taskCommand);

        assertEquals(event.taskInformation(Parser.parseFormat),
                reconstructedTask.taskInformation(Parser.parseFormat));
    }

    public void taskToCommandToTask_exceptionThrown () throws Event.EmptyDescriptionException {
        TaskStub taskStub = new TaskStub("testing task stub", true, LocalDateTime.now());
        String taskCommand = Parser.taskToCommand(taskStub);

        assertThrows(Parser.UnrecognisedCommandException.class, () -> {
            Parser.commandToTask(taskCommand);
        });
    }
}
