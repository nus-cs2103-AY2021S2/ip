package duke.task;

/**
 * ToDo task class (with time formatting feature).
 */
public class ToDo extends Task {
    public ToDo(String name, TaskType type) {
        super(name, type);
    }

    public ToDo(String name, TaskType type, boolean done) {
        super(name, type, done);
    }

}
