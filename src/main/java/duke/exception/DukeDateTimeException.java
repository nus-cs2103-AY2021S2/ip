package duke.exception;

/**
 * Class representing Date Time Exception in Duke, sub-class of DukeException.
 */
public class DukeDateTimeException extends DukeException {
    /**
     * Constructor of DukeDateTimeException.
     *
     * @param errorMsg Error message prompting user of invalid date/time.
     */
    public DukeDateTimeException(String errorMsg) {
        super(errorMsg);
    }
}
