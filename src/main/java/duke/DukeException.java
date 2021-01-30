package duke;

public class DukeException extends Exception {
    private static final long serialVersionUID = 7526472295622776147L;

    public DukeException() {
        super();
    }

    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Returns the message string of the error
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("☹ OOPS!!! %s", super.getMessage());
    }
}
