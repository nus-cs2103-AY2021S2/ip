import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.storage.Storage;
import duke.task.TaskList;

public class AddCommandTest {
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage("");

    @Test
    public void testExecuteTodo() throws IOException {
        try {
            String todoInput = "todo this";
            AddCommand addTodoCommand = new AddCommand(todoInput);

            addTodoCommand.execute(taskList, todoInput, storage);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.addToDo(String)\" because \"tasks\" is null",
                    e.getMessage());
        }
    }

    @Test
    public void testExecuteDeadline() throws IOException {
        try {
            String deadlineInput = "deadline return book /by 02/12/2019 18:00";
            AddCommand addDeadlineCommand = new AddCommand(deadlineInput);

            addDeadlineCommand.execute(taskList, deadlineInput, storage);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.addDeadline(String)\" because \"tasks\" is null",
                    e.getMessage());

        }
    }

    @Test
    public void testExecuteEvent() throws IOException {
        try {
            String eventInput = "event return book /at 02/12/2019 18:00";
            AddCommand addEventCommand = new AddCommand(eventInput);

            addEventCommand.execute(taskList, eventInput, storage);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.addEvent(String)\" because \"tasks\" is null",
                    e.getMessage());

        }
    }

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
