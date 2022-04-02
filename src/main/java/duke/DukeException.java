package duke;

/**
 * This class represents exceptions which might happen during execution of duke-related processes
 */
public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
