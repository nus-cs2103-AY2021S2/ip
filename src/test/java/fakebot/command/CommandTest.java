package fakebot.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {
    private CommandType type = CommandType.EVENT;
    private String description = "Test";

    @Test
    void getCommand_Equal() {
        Command test = new Command(type,description);
        assertEquals(type, test.getCommand(),"Command Does not match");
    }

    @Test
    void getDescription_Equal() {
        Command test = new Command(type,description);
        assertEquals(description, test.getDescription(),"Description Does not match");
    }
}