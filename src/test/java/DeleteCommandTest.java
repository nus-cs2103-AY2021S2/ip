import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteCommandTest {
    TaskList taskList;
    DataHandler dataHandler;

    String deleteInput = "delete 1";
    DeleteCommand deleteCommand = new DeleteCommand(deleteInput);

    @Test
    public void testExecuteDone() {
        try {
            deleteCommand.execute(taskList, deleteInput, dataHandler);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.getSize()\" because \"tasks\" is null",
                    e.getMessage());
        }
    }

    @Test
    public void testIsExit() {
        assertFalse(deleteCommand.isExit());
    }

}