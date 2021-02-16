import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;

public class AddCommandTest {

    @Test
    public void testIsExit() {
        String todoInput = "todo this";
        AddCommand addTodoCommand = new AddCommand(todoInput);

        String deadlineInput = "deadline return book /by 02/12/2019 18:00";
        AddCommand addDeadlineCommand = new AddCommand(deadlineInput);

        String eventInput = "event return book /at 02/12/2019 18:00";
        AddCommand addEventCommand = new AddCommand(eventInput);

        assertFalse(addTodoCommand.isExit());
        assertFalse(addDeadlineCommand.isExit());
        assertFalse(addEventCommand.isExit());
    }
}
