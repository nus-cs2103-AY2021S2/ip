public class ToDo extends Task {
    /**
     * Constructs a to-do task.
     * @param description a description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
