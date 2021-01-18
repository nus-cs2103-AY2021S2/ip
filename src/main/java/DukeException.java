public class DukeException extends Exception {
    protected final String typeException;
    protected final String taskName;

    DukeException(String typeException, String taskName) {
        this.typeException = typeException;
        this.taskName = taskName;
    }

    @Override
    public String getMessage() {
        String errorMsg;
        if(this.typeException.equals("invalid input")) {
            errorMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        } else {
            errorMsg = "☹ OOPS!!! The description of a " + this.taskName +
                    " cannot be " + this.typeException + ".";
        }
        return errorMsg;
    }
}
