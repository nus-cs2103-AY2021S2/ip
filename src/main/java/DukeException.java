/**
 * Custom Exception handling for DukeBot when handling commands
 */
public class DukeException extends Exception {
    private String command;
    private DukeExceptionType ExceptionType;

    /**
     * Constructor for DukeException
     *
     * @param command Command given by user
     * @param exceptionType Type of exception that is caught
     */
    public DukeException(String command, DukeExceptionType exceptionType) {
        super();
        this.command = command;
        this.ExceptionType = exceptionType;
    }

    /**
     * Returns the correct error message depending on exception
     *
     * @return Error message
     */
    @Override
    public String toString() {
        String errMsg = "";

        switch (ExceptionType) {
        case EMPTY_SELECTION:
            errMsg = "☹ OOPS!!! The selection for " + command + " cannot be empty.";
            break;
        case EMPTY_DESCRIPTION:
            errMsg = "☹ OOPS!!! The description of a " + command + " cannot be empty.";
            break;
        case INVALID_INTEGER:
            errMsg = "☹ OOPS!!! The selection for " + command + " should be a valid Integer.";
            break;
        case UNKNOWN_INPUT:
            errMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            break;
        case SELECTION_EXCEED_RANGE:
            errMsg = "☹ OOPS!!! The selection for " + command + " is not within the list's range.";
            break;
        default:
            break;
        }

        return errMsg;
    }
}
