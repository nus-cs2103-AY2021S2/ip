/**
 * Creates task objects categorised as a "to-do" task
 *
 * @author Amanda Ang
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object
     *
     * @param description the description of the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
