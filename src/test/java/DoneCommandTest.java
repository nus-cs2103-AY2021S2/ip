import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.command.DoneCommand;
import duke.storage.Storage;
import duke.task.TaskList;

public class DoneCommandTest {
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testExecuteDone() throws IOException {
        try {
            String doneInput = "done 1";
            DoneCommand doneCommand = new DoneCommand(doneInput);

            doneCommand.execute(taskList, doneInput, storage);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.getSize()\" because \"tasks\" is null",
                    e.getMessage());
        }
    }

    @Test
    public void testIsExit() {
        String doneInput = "done 1";
        DoneCommand doneCommand = new DoneCommand(doneInput);

        assertFalse(doneCommand.isExit());
    }

}
