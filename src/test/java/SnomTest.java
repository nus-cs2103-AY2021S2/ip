import commands.AddCommand;
import commands.Command;
import commands.CommandEnum;
import commands.ExitCommand;
import exceptions.SnomException;
import org.junit.jupiter.api.Test;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnomTest {
    @Test
    public void getCommandTest(){
        CommandEnum expectedCommand = CommandEnum.TODO;
        assertEquals(CommandEnum.getCommand("TODO"), expectedCommand);

        expectedCommand = CommandEnum.NONE;
        assertEquals(CommandEnum.getCommand("DUMMY"), expectedCommand);
    }

    @Test
    public void storageTest() throws SnomException {
        Storage storage = new Storage("./src/main/data", "snom.txt");
        TaskList taskList = new TaskList(storage.readFile());

        assertEquals(storage.readFile().size(), taskList.getSize());
    }
}
