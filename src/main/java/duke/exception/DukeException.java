package duke.exception;

/**
 * Custom Exception handling for DukeBot when handling commands
 */
public class DukeException extends Exception {
    private String command;
    private DukeExceptionType exceptionType;

    /**
     * Constructor for DukeException
     *
     * @param exceptionType Type of exception
     */
    public DukeException(DukeExceptionType exceptionType) {
        super();
        this.command = "";
        this.exceptionType = exceptionType;
    }

    /**
     * Constructor for DukeException
     *
     * @param command Command given by user
     * @param exceptionType Type of exception that is caught
     */
    public DukeException(String command, DukeExceptionType exceptionType) {
        super();
        this.command = command;
        this.exceptionType = exceptionType;
    }

    /**
     * Returns the correct error message depending on exception
     *
     * @return Error message
     */
    @Override
    public String toString() {
        String errMsg = "";

        switch (exceptionType) {
        case EMPTY_SELECTION:
            errMsg = "☹ OOPS!!! The selection for " + command + " cannot be empty.";
            break;
        case EMPTY_DESCRIPTION:
            errMsg = "☹ OOPS!!! The description of a " + command + " cannot be empty.";
            break;
        case EMPTY_KEYWORD:
            errMsg = "☹ OOPS!!! The keyword for " + command + " cannot be empty.";
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
        case INVALID_DATE_FORMAT:
            errMsg = "☹ OOPS!!! Please key in a valid date format (e.g. 2021-12-01) in yyyy-mm-dd format.";
            break;
        case LOAD_ERROR:
            errMsg = "☹ OOPS!!! The contents of the loaded file is corrupted, a new file will be created.";
            break;
        case SAVE_ERROR:
            errMsg = "☹ OOPS!!! Unable to write to Duke.Duke.txt, please try again.";
            break;
        case FILE_CREATION_ERROR:
            errMsg = "☹ OOPS!!! Unable to create file, please restart the program.";
            break;
        default:
            break;
        }

        return errMsg;
    }
}
