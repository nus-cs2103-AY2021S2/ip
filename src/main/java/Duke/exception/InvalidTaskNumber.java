package duke.exception;

/**
 * Represents a invalid task index number exception.
 */
public class InvalidTaskNumber extends DukeException {
    private int numberOfTasks;

    /**
     * Constructor for InvalidTaskNumber.
     *
     * @param numberOfTasks size of current task list.
     */
    public InvalidTaskNumber(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }
    /**
     * Returns String that produces error exception message to user.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "OOPS!!! Input task number from 1 to " + numberOfTasks + "\n";
    }
}
