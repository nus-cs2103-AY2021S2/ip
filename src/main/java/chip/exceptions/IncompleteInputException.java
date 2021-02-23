package chip.exceptions;

import chip.utils.Command;

/**
 * Exception thrown when input is incomplete.
 */
public class IncompleteInputException extends ChipException {
    private String message;

    /**
     * Creates an instance of exception thrown when input is incomplete.
     *
     * @param command Command that was called.
     */
    public IncompleteInputException(Command command) {
        super("");

        switch (command) {
        case TODO:
            message = "Todo requires a description :(";
            break;
        case EVENT:
            message = "Event requires a description and a valid date :(";
            break;
        case DEADLINE:
            message = "Deadline requires a description and a valid date :(";
            break;
        case DELETE:
            message = "Please enter a valid number after delete :(";
            break;
        case DONE:
            message = "Please enter a valid number after done :(";
            break;
        case FIND:
            message = "Arguments to find should not be empty :(";
            break;
        default:
            message = "I am not sure what happened. Please try again :(";
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
