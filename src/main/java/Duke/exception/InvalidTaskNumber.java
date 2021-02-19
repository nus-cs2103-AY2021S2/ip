package duke.exception;

public class InvalidTaskNumber extends DukeException {
    private int numberOfTasks;
    public InvalidTaskNumber(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks + 1;
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
