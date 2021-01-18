/**
 * A task for ToDo
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo
     * @param description Task name
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Includes task type
     * @return String format specific to ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
