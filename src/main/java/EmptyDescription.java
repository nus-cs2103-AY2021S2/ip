/**
 * Represent a empty task description parsed from user input.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */
public class EmptyDescription extends DukeException{
    protected String typeOfTask;

    /**
     * Returns EmptyDescription.
     *
     * @param typeOfTask
     * @return EmptyDescription
     */
    EmptyDescription(String typeOfTask) {
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
