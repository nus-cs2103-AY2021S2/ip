package duke;

public class DukeException extends Exception {
    private final String errMsg;
    /**
     * Constructs a DukeException object.
     * @param errMsg Error message
     */
    public DukeException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }
    public String getErrMsg() {
        return errMsg;
    }
}
