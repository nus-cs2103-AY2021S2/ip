import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DukeTest {
    @Test
    public void dukeCtor_fileNotFound_fileCreated() {
        new File("./data.txt").delete();
        assertDoesNotThrow(() -> new Duke("./data.txt"));
    }

    @Test
    public void executeCommand_invalidArg_exceptionCaught() {
        Duke sonia = new Duke("./data.txt");
        ArrayList<String> args = new ArrayList<>();
        Command c = new Command(CommandType.ADD_DEADLINE);
        assertDoesNotThrow(() -> sonia.executeCommand(c));
    }

    @Test
    public void deleteTask_outOfBoundsIndex_exceptionThrown() {
        TaskList tasks = new TaskList(new ArrayList<>());
        assertThrows(DukeInvalidArgumentException.class, () -> tasks.deleteTask(1));
    }
}
