package duke.task;

/**
 * ToDo task class (with time formatting feature).
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object.
     *
     * @param name event name
     * @param type type ToDo
     */
    public ToDo(String name, TaskType type) {
        super(name, type);
    }

    /**
     * Constructs a ToDo object with isDone status specified.
     *
     * @param name   event name
     * @param type   type ToDo
     * @param isDone the status of the event
     */
    public ToDo(String name, TaskType type, boolean isDone) {
        super(name, type, isDone);
    }

}
