package duke.tasks;

/**
 * Represents a ToDo {@code Task}.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo, specifying the description.
     *
     * @param description description of the ToDo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for ToDo, specifying the task's status and description.
     *
     * @param done integer value to indicate the ToDo's status
     * @param description description of the ToDo
     */
    public ToDo(int done, String description) {
        super(done, description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof ToDo) {
            ToDo toDo = (ToDo) obj;
            return toDo.description.equalsIgnoreCase(this.description);
        } else {
            return false;
        }
    }

    @Override
    public String toStorageString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
