/**
 * DukeException class to handle various types of exception in DukeBot
 */
public class DukeException extends Exception {
    protected final ExceptionType typeException;
    protected final String taskType;

    /**
     * Constructor for DukeException
     * @param typeException type of exception caught by the DukeBot
     * @param taskType type of task
     */
    public DukeException(ExceptionType typeException, String taskType) {
        super();
        this.typeException = typeException;
        this.taskType = taskType;
    }

    /**
     * A custom error message will be generated depending on exception type
     * @return error message
     */
    @Override
    public String getMessage() {
        String errorMsg;

        switch (this.typeException) {
            case INVALID_INPUT:
                errorMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                break;
            case INVALID_INTEGER:
                errorMsg = "☹ OOPS!!! The integer value is invalid (negative or out of task list range)!!!";
                break;
            default:
                errorMsg = "☹ OOPS!!! The description of a " + this.taskType +
                        " cannot be empty.";
                break;
        }
        return errorMsg;
    }
}
