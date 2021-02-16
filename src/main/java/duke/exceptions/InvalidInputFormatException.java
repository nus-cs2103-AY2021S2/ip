package duke.exceptions;

import duke.commands.CommandWord;

/**
 * class InvalidInputFormatException
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represents a DukeException when the user input has an invalid format
 */
public class InvalidInputFormatException extends DukeException {
    private String errorMessage;

    /**
     * Constructor: creates a new InvalidInputFormatException
     * @param commandWord
     */
    public InvalidInputFormatException(CommandWord commandWord) {
        super("");
        switch(commandWord) {
        case DEADLINE:
            errorMessage = "Please specify deadline in this format:\n" + "    deadline [description] /by [yyyy-mm-dd]\n"
                    + "(E.g. deadline project /by 2021-01-22";
            break;
        case DELETE:
            errorMessage = "Please specify a task number in this format:\n"
                    + "    delete [task number] (E.g. delete 2)";
            break;
        case DONE:
            errorMessage = "Please specify a task number in this format:\n" +
                    "    done [task number] (E.g. done 2)";
            break;
        case EVENT:
            errorMessage = "Please specify event in this format:\n" + "    event [description] /at [yyyy-mm-dd]\n"
                    + "(E.g. event wedding anniversary /at 2021-01-22";
            break;
        default:
            errorMessage = "Something went wrong with Duke!";
        }
    }

    /**
     * getMessage: retrieves error message
     * @return errorMessage
     */
    @Override
    public String getMessage() {
        String updatedErrorMessage = super.getMessage() + this.errorMessage;
        return updatedErrorMessage;
    }
}
