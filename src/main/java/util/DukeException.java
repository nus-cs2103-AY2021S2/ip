package util;

/**
 * Used to represent Exceptions related to parsing errors due to invalid user input.
 */
public class DukeException extends Exception {
    private final String msg;

    public DukeException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
