import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ListCommandTest {
    TaskList taskList;
    DataHandler dataHandler;

    String listInput = "foo";
    ListCommand listCommand = new ListCommand(listInput);

    @Test
    public void testExecuteDone() {
        try {
            listCommand.execute(taskList, listInput, dataHandler);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.list()\" because \"tasks\" is null",
                    e.getMessage());
        }
    }

    @Test
    public void testIsExit() {
        assertFalse(listCommand.isExit());
    }

}