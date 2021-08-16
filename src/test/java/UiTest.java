import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.BobException;
import bob.command.Command;
import bob.processor.Storage;
import bob.task.TaskList;

public class UiTest {

    @Test
    public void respondToCommand_validHelpCommand_correctResponse() throws BobException {
        String userInput = "help";
        Storage storage = new Storage("data/tasks.txt");
        TaskList taskList;
        try {
            taskList = storage.load();
        } catch (BobException e) {
            taskList = new TaskList();
        }

        String expectedResponse = "You can add a new task using the following commands: \n"
                + "todo NAME_OF_TASK\n"
                + "event NAME_OF_EVENT /at: YYYY-MM-DD HHMM\n"
                + "deadline NAME_OF_DEADLINE /by: YYYY-MM-DD HHMM\n"
                + "\nYou can see the whole list of tasks using: list\n"
                + "\nTo search for a specific task: find KEYWORDS\n"
                + "\nTo add a reminder for a task:\n"
                + "remind INDEX /on: YYYY-MM-DD HHMM\n"
                + "\nTo mark a task as done: done INDEX\n"
                + "\nTo delete a task: delete INDEX\n"
                + "\nTo exit the app: bye";
        String actualResponse = Command.HELP.executeCommand(userInput, taskList, storage);
        assertEquals(expectedResponse, actualResponse);
    }
}
