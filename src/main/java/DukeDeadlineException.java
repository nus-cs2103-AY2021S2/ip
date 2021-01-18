public class DukeDeadlineException extends DukeException {

    /**
     * Constructor for DukeDeadlineException.
     * @param errorMsg Error message to prompt user to enter a deadline for the task.
     */

    public DukeDeadlineException(String errorMsg) {
        super(errorMsg);
    }
}
