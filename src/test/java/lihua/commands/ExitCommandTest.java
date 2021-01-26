package lihua.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {
    @Test
    public void commandDescription_noGivenInput_descriptionMatches() {
        String EXPECTED = "bye: Exit the application. Data will be auto-saved.\n"
                + "---- Example: bye";
        assertEquals(EXPECTED, ExitCommand.MESSAGE_USAGE);
    }
}
