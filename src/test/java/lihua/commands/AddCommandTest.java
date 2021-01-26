package lihua.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void commandDescription_noGivenInput_descriptionMatches() {
        String EXPECTED = "todo/deadline/event: add a task of a specific type to the task list.\n"
                + "---- Example 1: todo [task name]\n" + "---- Example 2: deadline [task name] /by [yyyy-mm-dd]\n" +
                "---- Example 3: event [task name] /at [yyyy-mm-dd]";
        assertEquals(EXPECTED, AddCommand.MESSAGE_USAGE);
    }
}
