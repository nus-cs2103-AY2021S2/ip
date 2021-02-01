package fakebot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CommandTest {
    private CommandType type = CommandType.EVENT;
    private String description = "Test";

    @Test
    void getCommand_equal() {
        Command test = new Command(type, description);
        assertEquals(type, test.getCommand(), "Command Does not match");
    }

    @Test
    void getDescription_equal() {
        Command test = new Command(type, description);
        assertEquals(description, test.getDescription(), "Description Does not match");
    }
}
