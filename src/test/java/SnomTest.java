import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import snom.common.exceptions.SnomException;
import snom.logic.commands.CommandEnum;
import snom.model.task.TaskList;
import snom.storage.StorageManager;

public class SnomTest {
    @Test
    public void getCommandTest() {
        CommandEnum expectedCommand = CommandEnum.TODO;
        assertEquals(CommandEnum.getCommand("TODO"), expectedCommand);

        expectedCommand = CommandEnum.NONE;
        assertEquals(CommandEnum.getCommand("DUMMY"), expectedCommand);
    }

    @Test
    public void storageTest() throws SnomException {
        StorageManager storage = new StorageManager("./src/main/data/snom.txt");
        TaskList taskList = new TaskList(storage.importTask());

        assertEquals(storage.readFile().size(), taskList.getSize());
    }
}
