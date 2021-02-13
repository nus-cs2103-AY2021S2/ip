package duke.task;

/**
 * An enum with all possible types of tasks.
 */
public enum TaskType {
    TODO("T"), DEADLINE("D"), EVENT("E");

    private final String type;

    /**
     * Constructs a task type object for a task.
     *
     * @param type the task type
     */
    TaskType(String type) {
        this.type = type;
    }

    /**
     * Returns the type of the task.
     *
     * @return the task type to be returned
     */
    public String getType() {
        return type;
    }

}
