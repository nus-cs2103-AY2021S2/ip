/**
 * A task for ToDo
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo
     *
     * @param description Task name
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String writeContentFormat() {
        return String.format("T | %s\n", super.writeContentFormat());
    }

    /**
     * Returns task type, status icon and task name
     *
     * @return String format specific to ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
