package duke;

/**
 * A type of task that is to be done without any associated date for completion.
 */

public class ToDo extends Task {
    public ToDo(String description) {
        super(description, "T");
    }

    /**
     * String representation of Todo. For example : "[T][] Read book"
     *
     * @return String representation of ToDo
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
