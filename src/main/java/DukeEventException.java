public class DukeEventException extends DukeException {

    /**
     * Constructor for DukeEventException.
     * @param errorMsg Error message to prompt user to enter a date/time for the event.
     */

    public DukeEventException(String errorMsg) {
        super(errorMsg);
    }
}
