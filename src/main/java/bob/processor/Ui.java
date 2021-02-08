package bob.processor;

import bob.command.Command;
import bob.task.TaskList;

public class Ui {

    /**
     * Returns an error message when the file containing the list of tasks
     * cannot be found.
     */
    public String showLoadingError() {
        return "There was an error in loading the file as it could not be found.";
    }
    
    /**
     * Provide relevant responses to user and accurate actions according to
     * user's command.
     * @param userInput User's command
     * @param taskList The list of tasks user has
     * @param storage The storage containing the saved list of task
     * @return A boolean value indicating whether to continue taking in commands or not
     */
    public String respondToCommand(String userInput, TaskList taskList, Storage storage) {
        String[] wordsInInput = userInput.split(" ", 2);
        String command = wordsInInput[0].toLowerCase();

        switch (command) {
        case "bye":
            return Command.BYE.executeCommand(userInput, taskList, storage);
        case "list":
            return Command.LIST.executeCommand(userInput, taskList, storage);
        case "find":
            return Command.FIND.executeCommand(userInput, taskList, storage);
        case "done":
            return Command.DONE.executeCommand(userInput, taskList, storage);
        case "delete":
            return Command.DELETE.executeCommand(userInput, taskList, storage);
        case "todo":
            return Command.TODO.executeCommand(userInput, taskList, storage);
        case "event":
            return Command.EVENT.executeCommand(userInput, taskList, storage);
        case "deadline":
            return Command.DEADLINE.executeCommand(userInput, taskList, storage);
        default:
            return Command.INVALID.executeCommand(userInput, taskList, storage);
        }
    }
}
