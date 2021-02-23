package duke;

import java.util.List;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.MissingTaskNumberException;
import duke.task.Task;

/**
 * <code>Parser</code> class deals with making sense of the user command.
 */
public class Parser {
    protected static String task;
    /**
     * Constructor for Parser class.
     *
     */
    public Parser() {
    }

    /**
     * Parses the String user input into an array of Strings.
     *
     * @return An array of Strings of the parsed user input.
     */
    public String[] parseUserInput(String userInput) {
        return userInput.split(" ");
    }

    /**
     * Parses the String of the new detail for the update function.
     *
     * @param parsedUserInput String array of user input.
     * @return String of the new details to be updated.
     */
    public String parseUpdateInput(String[] parsedUserInput) {
        StringBuilder newDetail = new StringBuilder();

        for (int i = 3; i < parsedUserInput.length; i++) {
            newDetail.append(parsedUserInput[i] + " ");
        }

        return newDetail.toString();
    }

    /**
     * Checks the user input and ensures that it makes sense.
     *
     * @param list List of existing tasks.
     * @throws MissingTaskNumberException If there is no task number for done or delete actions.
     * @throws InvalidTaskNumberException If the task number is outside the list range.
     * @throws EmptyDescriptionException If there is no description for the task action.
     */
    public void checkUserInput(List<Task> list, String userInput)
            throws MissingTaskNumberException,
            InvalidTaskNumberException,
            EmptyDescriptionException,
            InvalidInputException {
        String[] userInputArr = userInput.split(" ");
        String task = userInputArr[0];

        switch (task) {
        case "list":
        case "bye":
            if (userInputArr.length > 1) {
                throw new InvalidInputException();
            }
            break;

        case "todo":
        case "deadline":
        case "event":
        case "find":
            if (userInputArr.length == 1) {
                throw new EmptyDescriptionException(task);
            }
            break;

        case "done":
        case "delete":
            if (userInputArr.length == 1) {
                throw new MissingTaskNumberException();
            }

            int taskNumber = Integer.parseInt(userInputArr[1]) - 1;

            if (taskNumber < 0 || taskNumber > list.size() - 1) {
                throw new InvalidTaskNumberException();
            }
            break;

        case "update":
            if (userInputArr.length < 4) {
                throw new EmptyDescriptionException(task);
            }
            break;

        default:
            throw new InvalidInputException();
        }
    }
}
