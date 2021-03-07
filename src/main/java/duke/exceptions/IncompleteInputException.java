package duke.exceptions;

import duke.commands.CommandWord;

/**
 * class IncompleteInputException
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represents a DukeException when the user input is incomplete
 */
public class IncompleteInputException extends DukeException{
    private String errorMessage;

    /**
     * Constructor: creates a new IncompleteInputException
     * @param commandWord
     */
    public IncompleteInputException(CommandWord commandWord) {
        super("");
        switch(commandWord) {
        case DEADLINE:
            errorMessage = "Deadline description cannot be omitted!";
            break;
        case DELETE:
            errorMessage = "Index of task to delete cannot be omitted!";
            break;
        case DONE:
            errorMessage = "Index of task to be marked as done cannot be omitted!";
            break;
        case EVENT:
            errorMessage = "Event description cannot be omitted!";
            break;
        case FIND:
            errorMessage = "Keyword to find specific tasks cannot be omitted!";
            break;
        case TODO:
            errorMessage = "ToDo description cannot be omitted!";
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
