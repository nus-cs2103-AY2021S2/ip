/**
 * Represent a Deadline item, which is a child of Task
 */
public class Deadline extends Task {
    private String by;

    /**
     * Initialize a Deadline item with a given end time
     * @param name The name of the deadline
     * @param by The end time of the item
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * toString method overriding the one in class Task
     * @return a user-friendly String representation of the Deadline item
     */
    @Override
    public String toString() {
        String doneMark = isDone? "X": " ";
        return String.format("[D][%s] %s (by: %s)", doneMark, name, by);
    }
}
