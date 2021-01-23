import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DoneCommandTest {
    TaskList taskList;
    DataHandler dataHandler;

    String doneInput = "done 1";
    DoneCommand doneCommand = new DoneCommand(doneInput);

    @Test
    public void testExecuteDone() {
        try {
            doneCommand.execute(taskList, doneInput, dataHandler);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.getSize()\" because \"tasks\" is null",
                    e.getMessage());
        }
    }

    @Test
    public void testIsExit() {
        assertFalse(doneCommand.isExit());
    }

}