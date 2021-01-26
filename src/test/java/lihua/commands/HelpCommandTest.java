package lihua.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    @Test
    public void commandDescription_noGivenInput_descriptionMatches() {
        String EXPECTED = "help: Shows application usage instructions.\n"
                + "---- Example: help";
        assertEquals(EXPECTED, HelpCommand.MESSAGE_USAGE);
    }
}
