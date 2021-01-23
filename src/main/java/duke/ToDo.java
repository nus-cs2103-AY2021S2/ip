package duke;

/**
 * ToDo task class (with time formatting feature).
 */
public class ToDo extends Task {
    ToDo(String name, TaskType type) {
        super(name, type);
    }

    ToDo(String name, TaskType type, boolean done) {
        super(name, type, done);
    }

}
