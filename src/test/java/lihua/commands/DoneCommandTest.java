package lihua.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    @Test
    public void commandDescription_noGivenInput_descriptionMatches() {
        String expected = "done: Get a specific task done.\n"
                + "---- Example: done [valid index number]";
        assertEquals(expected, DoneCommand.MESSAGE_USAGE);
    }
}