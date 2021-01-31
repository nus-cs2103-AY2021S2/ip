import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.command.DoneCommand;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class DoneCommandTest {
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testExecuteDone() {
        try {
            String doneInput = "done 1";
            DoneCommand doneCommand = new DoneCommand(doneInput);

            doneCommand.execute(taskList, doneInput, storage);
        } catch (IndexOutOfBoundsException | DukeException e) {
            assertEquals("Index 0 out of bounds for length 0", e.getMessage());
        }
    }

    @Test
    public void testIsExit() {
        String doneInput = "done 1";
        DoneCommand doneCommand = new DoneCommand(doneInput);

        assertFalse(doneCommand.isExit());
    }

}
