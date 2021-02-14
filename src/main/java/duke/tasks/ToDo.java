package duke.tasks;

/**
 * Represents a ToDo {@code Task}.
 */
public class ToDo extends Task {
    private static final String STRING_FORMAT = "[T]%s";
    private static final String STORAGE_STRING_FORMAT = "T | %d | %s";

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
        return String.format(STORAGE_STRING_FORMAT, isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.toString());
    }
}
