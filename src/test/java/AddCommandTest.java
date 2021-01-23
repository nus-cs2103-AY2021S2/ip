import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddCommandTest {
    TaskList taskList;
    DataHandler dataHandler;

    String todoInput = "todo this";
    AddCommand addTodoCommand = new AddCommand(todoInput);

    String deadlineInput = "deadline return book /by 02/12/2019 18:00";
    AddCommand addDeadlineCommand = new AddCommand(deadlineInput);

    String eventInput = "event return book /at 02/12/2019 18:00";
    AddCommand addEventCommand = new AddCommand(deadlineInput);


    @Test
    public void testExecuteTodo(){
        try {
            addTodoCommand.execute(taskList, todoInput, dataHandler);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.addToDo(String)\" because \"tasks\" is null",
                    e.getMessage());
        }
    }

    @Test
    public void testExecuteDeadline(){
        try {
            addDeadlineCommand.execute(taskList, deadlineInput, dataHandler);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.addDeadline(String)\" because \"tasks\" is null",
                    e.getMessage());

        }
    }

    @Test
    public void testEventTodo(){
        try {
            addTodoCommand.execute(taskList, eventInput, dataHandler);
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"TaskList.addEvent(String)\" because \"tasks\" is null",
                    e.getMessage());

        }
    }

    @Test
    public void testIsExit() {
        assertFalse(addTodoCommand.isExit());
        assertFalse(addDeadlineCommand.isExit());
        assertFalse(addEventCommand.isExit());
    }
}