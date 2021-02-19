package lihua.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeleteCommandTest {
    @Test
    public void commandDescription_noGivenInput_descriptionMatches() {
        String expected = "delete: Remove a specific task from the list.\n"
                + "---- Example: delete [valid index number]";
        assertEquals(expected, DeleteCommand.MESSAGE_USAGE);
    }
}
