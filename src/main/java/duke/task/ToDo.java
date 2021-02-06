package duke.task;

/**
 * ToDo task class (with time formatting feature).
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object.
     *
     * @param name event name
     */
    public ToDo(String name) {
        super(name, TaskType.TODO);
    }

    /**
     * Constructs a ToDo object with isDone status specified.
     *
     * @param name   event name
     * @param isDone the status of the event
     */
    public ToDo(String name, boolean isDone) {
        super(name, TaskType.TODO, isDone);
    }

}
