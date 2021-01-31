package lihua.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AddCommandTest {
    @Test
    public void commandDescription_noGivenInput_descriptionMatches() {
        String expected = "todo/deadline/event: add a task of a specific type to the task list.\n"
                + "---- Example 1: todo [task name]\n" + "---- Example 2: deadline [task name] /by [yyyy-mm-dd]\n"
                + "---- Example 3: event [task name] /at [yyyy-mm-dd]";
        assertEquals(expected, AddCommand.MESSAGE_USAGE);
    }
}
