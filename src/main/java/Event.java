/**
 * Class Event is an extension of the Task class.
 * @author Zhang Peng.
 * @version 21 Jan 2021.
 */
public class Event extends Task {

    protected String by;
    /**
     * This is a constructor for the class.
     */
    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * This is the toString() method of the class.
     * @return String This returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + by + ")";
    }
}
