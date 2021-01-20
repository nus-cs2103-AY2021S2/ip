public class DukeException extends Exception {
    protected final ExceptionType typeException;
    protected final String taskName;

    public DukeException(ExceptionType typeException, String taskName) {
        super();
        this.typeException = typeException;
        this.taskName = taskName;
    }

    @Override
    public String getMessage() {
        String errorMsg;

        switch (this.typeException) {
            case INVALID_INPUT:
                errorMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                break;
            case INVALID_INTEGER:
                errorMsg = "☹ OOPS!!! The integer value is invalid (negative or out of list range)!!!";
                break;
            default:
                errorMsg = "☹ OOPS!!! The description of a " + this.taskName +
                        " cannot be empty.";
                break;
        }
        return errorMsg;
    }
}
