/**
 * Class to handle Exceptions specific to Duke
 */
public class DukeException extends Exception {

    /**
     * Creates an DukeException instance.
     *
     * @param errorMessage String describing the error
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
