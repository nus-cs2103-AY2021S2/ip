package exception;

/**
 * duke.Duke unknown input exception.
 */
public class DukeUnknownInputException extends DukeException {
    /**
     * Instantiates a duke.Duke unknown input exception.
     */
    public DukeUnknownInputException() {
        super("Oops! I'm not quite sure what you meant there.");
    }
}
