package lihua.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ListCommandTest {
    @Test
    public void commandDescription_noGivenInput_descriptionMatches() {
        String expected = "list: List down all the tasks. "
                + "List down all the tasks on a specific date, if additional date argument is given\n"
                + "List down all time-related tasks in a chronological order, if additional tag -t is given.\n"
                + "---- Example 1: list\n"
                + "---- Example 2: list [yyyy-mm-dd]\n"
                + "---- Example 3: list -time";
        assertEquals(expected, ListCommand.MESSAGE_USAGE);
    }
}
