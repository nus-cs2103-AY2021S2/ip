package exception;

/**
 * duke.Duke no input exception.
 */
public class DukeNoInputException extends DukeException {
    /**
     * Instantiates a new duke.Duke exception.
     */
    public DukeNoInputException() {
        super("Oops, I cannot process nothing as input!");
    }
}
