package snom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import snom.common.exceptions.SnomException;
import snom.logic.commands.CommandEnum;
import snom.model.task.TaskList;
import snom.storage.StorageManager;

import java.nio.file.Paths;

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
        StorageManager storage = new StorageManager(Paths.get("data", "snom_test.txt"));
        TaskList taskList = storage.importTask();

        assertEquals(storage.readFile().size(), taskList.size());
    }
}
