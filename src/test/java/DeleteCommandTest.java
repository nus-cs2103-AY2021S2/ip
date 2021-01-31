import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.command.DeleteCommand;
import duke.storage.Storage;
import duke.task.TaskList;

public class DeleteCommandTest {
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testExecuteDone() throws IOException {
        try {
            String deleteInput = "delete 1";
            DeleteCommand deleteCommand = new DeleteCommand(deleteInput);

            deleteCommand.execute(taskList, deleteInput, storage);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.getSize()\" because \"tasks\" is null",
                    e.getMessage());
        }
    }

    @Test
    public void testIsExit() {
        String deleteInput = "delete 1";
        DeleteCommand deleteCommand = new DeleteCommand(deleteInput);

        assertFalse(deleteCommand.isExit());
    }

}
