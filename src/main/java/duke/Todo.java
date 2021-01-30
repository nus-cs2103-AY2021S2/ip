package duke;

/**
 * The simplest type of Task with no date and time.
 */
public class Todo extends Task {

    /**
     * Creates a Todo
     *
     * @param description the name of the Todo
     */
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Marks a Todo as done.
     *
     * @return a new Todo that is considered done
     */
    @Override
    public Todo markAsDone() {
        return new Todo(description, true);
    }

    /**
     * Formats a Todo for storing in the file.
     *
     * @return a String representation of the Todo
     */
    public String fileFormat() {
        return "T | " + (super.isDone ? "1 | " : "0 | ") + this.description;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return obj instanceof Todo && description.equals(((Todo) obj).getDescription())
                    && isDone == ((Todo) obj).isDone();
        }
    }
}
