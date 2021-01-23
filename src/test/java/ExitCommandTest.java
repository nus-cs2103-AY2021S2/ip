import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {
    TaskList taskList;
    DataHandler dataHandler;

    String exitInput = "bye";
    ExitCommand exitCommand = new ExitCommand(exitInput);

    @Test
    public void testExecuteDone() {
        try {
            exitCommand.execute(taskList, exitInput, dataHandler);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.addExit(String)\" because \"tasks\" is null",
                    e.getMessage());
        }
    }

    @Test
    public void testIsExit() {
        assertTrue(exitCommand.isExit());
    }

}