package duke.exception;

/**
 * Represents a duplicateTask exception from duplicate user input.
 */
public class DuplicateTask extends DukeException {

    public DuplicateTask() {

    }

    /**
     * Returns String of error message.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "OOPS!!! Task already exists. Please try again.\n";
    }
}
