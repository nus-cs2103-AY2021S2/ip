package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/**
 * The Parser class handles user input, classifies the
 * input based on a command and directs the execution
 * of the command.
 */
public class Parser {
    /**
     * Attempts to parse user input into a Command
     * which an instance of Duke can execute.
     * @param userInput The raw string user input.
     * @return The corresponding Command.
     * @throws DukeException Error if user input is not in an appropriate format.
     */
    public static Command parseCommand(String userInput) throws DukeException {
        userInput = userInput.trim();
        checkValidInput(userInput);
        String[] userInputArr = userInput.split(" ", 2);
        CommandType commandType = CommandType.valueOfLabel(userInputArr[0]);

        switch (commandType) {
        case TODO:
            return new TodoCommand(userInputArr[1]);
        case DEADLINE:
            return new DeadlineCommand(userInputArr[1]);
        case EVENT:
            return new EventCommand(userInputArr[1]);
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand(userInputArr[1]);
        case DELETE:
            return new DeleteCommand(userInputArr[1]);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    private static void checkValidInput(String userInput) throws DukeException {

    }
}
