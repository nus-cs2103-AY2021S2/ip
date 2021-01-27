package blarb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void command_success() {
        assertEquals(Command.BYE, Command.command("bYe"));
        assertEquals(Command.UNKNOWN, Command.command("asdfjak"));
        assertEquals(Command.UNKNOWN, Command.command(""));
    }
}
