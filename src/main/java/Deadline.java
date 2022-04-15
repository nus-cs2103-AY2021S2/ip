/**
 * Class Deadline is an extension of the Task class.
 * @author Zhang Peng.
 * @version 21 Jan 2021.
 */
public class Deadline extends Task {

    protected String by;
    /**
     * This is a constructor for the class.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * This is a toString method to print out the Deadline object.
     * @return String an string representing the object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
