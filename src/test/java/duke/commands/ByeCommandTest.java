package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ByeCommandTest {
    @Test
    public void isByeCommand_byeCommand_true() {
        assertTrue(ByeCommand.isByeCommand(new ByeCommand()));
    }

    @Test
    public void isByeCommand_nullInput_false() {
        assertFalse(ByeCommand.isByeCommand(null));
    }

    @Test
    public void execute_exit_success() {
        CommandResult expected = new CommandResult("Exiting...");
        assertEquals(expected, new ByeCommand().execute());
    }
}
