import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.command.DeleteCommand;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class DeleteCommandTest {
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testExecuteDelete() throws DukeException {
        try {
            String deleteInput = "delete 1";
            DeleteCommand deleteCommand = new DeleteCommand(deleteInput);

            deleteCommand.execute(taskList, deleteInput, storage);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 0 out of bounds for length 0", e.getMessage());
        }
    }

    @Test
    public void testIsExit() {
        String deleteInput = "delete 1";
        DeleteCommand deleteCommand = new DeleteCommand(deleteInput);

        assertFalse(deleteCommand.isExit());
    }

}
