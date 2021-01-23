package blarb;

/**
 * {@code ToDo} is a {@code Task} that does not have further details attached.
 *
 * @see Task
 */
class ToDo extends Task {
    /**
     * Constructs a new uncompleted {@code ToDo}.
     *
     * @param description The name of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts {@code Todo} into string format to be stored in file.
     *
     * @return String format to be stored in file.
     */
    public String encode() {
        return String.format("T / %s", super.encode());
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
