package duke;

import java.lang.Exception;

public class DukeException extends Exception {
    private static final long serialVersionUID = 7526472295622776147L;

    public DukeException() {
        super();
    }

    public DukeException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! %s", super.getMessage());
    }
}
