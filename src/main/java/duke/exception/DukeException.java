package duke.exception;

/**
 * DukeException class to handle various types of exception in DukeBot
 */
public class DukeException extends Exception {
    protected final ExceptionType typeException;
    protected final String taskType;

    /**
     * Constructor for DukeException
     *
     * @param typeException Type of exception caught by the DukeBot
     * @param taskType Type of task
     */
    public DukeException(ExceptionType typeException, String taskType) {
        super();
        this.typeException = typeException;
        this.taskType = taskType;
    }

    /**
     * A custom error message will be generated depending on exception type
     *
     * @return Error message
     */
    @Override
    public String getMessage() {
        String errorMsg = "";

        switch (this.typeException) {
        case INVALID_INPUT:
            errorMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            break;
        case INVALID_INTEGER:
            errorMsg = "☹ OOPS!!! The integer value is invalid (negative, 0 or out of task list range) :-(";
            break;
        case INVALID_DATETIME:
            errorMsg = "☹ OOPS!!! Invalid date has been detected :-( "
                    + "Pls key in a valid date in MMM dd yyyy format (i.e. Oct 15 2019) !!!";
            break;
        case BLANK_DESCRIPTION:
            errorMsg = "☹ OOPS!!! The description of a " + this.taskType + " cannot be empty :-(";
            break;
        case LOADING_ERROR:
            errorMsg = "☹ OOPS!!! The file cannot be loaded and a new file will be created !!!";
            break;
        case SAVING_ERROR:
            errorMsg = "☹ OOPS!!! Unfortunately, file saving to Duke.txt is not working at the moment :-( "
                    + "Pls kindly try again later !!!";
            break;
        case INVALID_FILE_CONFIGURATION:
            errorMsg = "☹ OOPS!!! Unfortunately, file configuration is not working at the moment :-( "
                    + "Pls restart (exit and start again) the Duke Bot !!!";
            break;
        default:
            break;
        }
        return errorMsg;
    }
}
