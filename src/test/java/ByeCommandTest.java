import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.ByeCommand;
import duke.storage.Storage;
import duke.task.TaskList;

public class ByeCommandTest {
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testExecuteDone() {
        try {
            String exitInput = "bye";
            ByeCommand exitCommand = new ByeCommand(exitInput);

            exitCommand.execute(taskList, exitInput, storage);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.addExit(String)\" because \"tasks\" is null",
                    e.getMessage());
        }
    }

    @Test
    public void testIsExit() {
        String exitInput = "bye";
        ByeCommand exitCommand = new ByeCommand(exitInput);

        assertTrue(exitCommand.isExit());
    }

}
