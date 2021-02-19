package duke.exception;

/**
 * Represent a empty task description parsed from user input.
 */
public class EmptyDescription extends DukeException {
    private String typeOfTask;

    /**
     * Returns EmptyDescription.
     *
     * @param typeOfTask
     * @return EmptyDescription
     */
    public EmptyDescription(String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    /**
     * Returns String that produces error exception message to user.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "OOPS!!! The description of a " + this.typeOfTask + " cannot be empty.\n";
    }
}
