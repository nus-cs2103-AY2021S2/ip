package lihua.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExitCommandTest {
    @Test
    public void commandDescription_noGivenInput_descriptionMatches() {
        String expected = "bye: Exit the application. Data will be auto-saved.\n"
                + "---- Example: bye";
        assertEquals(expected, ExitCommand.MESSAGE_USAGE);
    }
}
