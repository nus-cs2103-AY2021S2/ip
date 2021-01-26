package lihua.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void commandDescription_noGivenInput_descriptionMatches() {
        String EXPECTED = "delete: Remove a specific task from the list.\n"
                + "---- Example: delete [valid index number]";
        assertEquals(EXPECTED, DeleteCommand.MESSAGE_USAGE);
    }
}
