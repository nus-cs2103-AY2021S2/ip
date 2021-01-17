/**
 * Represent a Deadline item, which is a child of Task
 */
public class Deadline extends Task {
    private String by;

    /**
     * Initialize a Deadline item with a given end date
     * @param name The name of the deadline
     * @param by The deadline of the item
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * toString method overriding the one in class Task
     * @return the user-friendly String representation of the Deadline item
     */
    @Override
    public String toString() {
        String doneMark = isDone? "X": " ";
        return String.format("[D][%s] %s", doneMark, name);
    }
}
