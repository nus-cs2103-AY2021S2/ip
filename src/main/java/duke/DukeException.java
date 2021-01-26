package duke;

/**
 * Class DukeException represents an exception resulted due to wrong format of command
 * entered into Danh"s Duke
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
