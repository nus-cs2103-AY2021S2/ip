/**
 * {@code ToDo} is a {@code Task} that does not have further details attached.
 *
 * @see Task
 */
public class ToDo extends Task {
    /**
     * Constructs a new uncompleted {@code ToDo}.
     *
     * @param description The name of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of the ToDo.
     *
     * @return ToDo in check list form.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
