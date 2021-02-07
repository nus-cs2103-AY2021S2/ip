package bob;

import bob.command.Command;
import bob.task.Deadline;
import bob.task.Event;

import java.util.Scanner;

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
        String[] wordsInInput = userInput.split(" ");
        Parser parser = new Parser();
        Command command = parser.parseCommand(wordsInInput);

        switch (command) {
        case BYE:
            return Command.BYE.executeCommand(userInput, taskList, storage);
        case LIST:
            return Command.LIST.executeCommand(userInput, taskList, storage);
        case FIND:
            return Command.FIND.executeCommand(userInput, taskList, storage);
        case DONE:
            return Command.DONE.executeCommand(userInput, taskList, storage);
        case DELETE:
            return Command.DELETE.executeCommand(userInput, taskList, storage);
        case TODO:
            return Command.TODO.executeCommand(userInput, taskList, storage);
        case EVENT:
            return Command.EVENT.executeCommand(userInput, taskList, storage);
        case DEADLINE:
            return Command.DEADLINE.executeCommand(userInput, taskList, storage);
        case INVALID:
            return Command.INVALID.executeCommand(userInput, taskList, storage);
        default:
            return "";
        }
    }
}
