import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.command.ListCommand;
import duke.storage.Storage;
import duke.task.TaskList;

public class ListCommandTest {
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage("");

    @Test
    public void testExecuteDone() {
        try {
            String listInput = "foo";
            ListCommand listCommand = new ListCommand(listInput);

            listCommand.execute(taskList, listInput, storage);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.list()\" because \"tasks\" is null",
                    e.getMessage());
        }
    }

    @Test
    public void testIsExit() {
        String listInput = "foo";
        ListCommand listCommand = new ListCommand(listInput);

        assertFalse(listCommand.isExit());
    }

}
