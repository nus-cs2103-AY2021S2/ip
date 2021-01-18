public class DukeException extends Exception {
    protected final String typeException;
    protected final String taskName;

    DukeException(String typeException, String taskName) {
        this.typeException = typeException;
        this.taskName = taskName;
    }

    @Override
    public String getMessage() {
        String errorMsg = "";

        switch (this.typeException) {
            case "invalid input":
                errorMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                break;
            case "invalid integer value":
                errorMsg = "☹ OOPS!!! The integer value is invalid!!!";
                break;
            default:
                errorMsg = "☹ OOPS!!! The description of a " + this.taskName +
                        " cannot be " + this.typeException + ".";
                break;
        }
        return errorMsg;
    }
}
