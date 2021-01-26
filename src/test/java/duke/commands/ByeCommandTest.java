package duke.commands;

import org.junit.jupiter.api.Test;

import static duke.commands.ByeCommand.EXIT_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {
    @Test
    public void isByeCommand_byeCommand_success() {
        assertTrue(ByeCommand.isByeCommand(new ByeCommand()));
    }
    
    @Test
    public void isByeCommand_nullInput_fail() {
        assertFalse(ByeCommand.isByeCommand(null));
    }
    
    @Test
    public void execute_exit_success() {
        CommandResult expected = new CommandResult(EXIT_MESSAGE);
        assertEquals(expected, new ByeCommand().execute());
    }
}
