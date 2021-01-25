package lihua.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void commandDescription_noGivenInput_descriptionMatches() {
        String expected = "list: List done all the tasks. " +
                "List done all the tasks on a specific date, if additional date argument is given\n"
                + "---- Example 1: list\n"
                + "---- Example 2: list [yyyy-mm-dd]";
        assertEquals(expected, ListCommand.MESSAGE_USAGE);
    }
}
