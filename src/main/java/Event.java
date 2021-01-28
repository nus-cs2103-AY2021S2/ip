/**
 * Class Event is an extension of the Task class.
 *
 * @version 28 Jan 2021
 * @author Zhang Peng
 */

public class Event extends Task {

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }
    /**
     * This is the toString() method of the class
     * @return String This returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + by + ")";
    }
}
