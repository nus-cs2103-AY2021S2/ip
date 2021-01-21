public class DukeException extends Exception {
    private static final String ERROR_MESSAGE = "☹ OOPS!!! unknown command or incorrect input format";

    public DukeException() {
        super(ERROR_MESSAGE);
    }

    public DukeException(String msg) {
        super(msg);
    }
}
