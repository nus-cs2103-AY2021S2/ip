package task;

import java.util.List;

import duke.DukeException;

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

    public Todo(String description, boolean isDone, List<String> tags) {
        super(description, isDone, tags);
    }

    /**
     * Marks a Todo as done.
     * @throws DukeException if the Todo is already marked as done.
     * @return a new Todo that is considered done
     */
    @Override
    public Todo markAsDone() throws DukeException {
        if (isDone) {
            throw new DukeException("This Task has already been marked as done!");
        }
        return new Todo(description, true, tags);
    }

    /**
     * Formats a Todo for storing in the file.
     *
     * @return a String representation of the Todo
     */
    public String fileFormat() {
        return "T | " + (super.isDone ? "1 | " : "0 | ") + this.description + " | " + tags;
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
