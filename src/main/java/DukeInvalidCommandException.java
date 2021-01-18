public class DukeInvalidCommandException extends DukeException {

    /**
     * Constructor for DukeInvalidCommandException.
     * @param errorMsg Error message to prompt user to enter a valid command.
     */

    public DukeInvalidCommandException(String errorMsg) {
        super(errorMsg);
    }
}
