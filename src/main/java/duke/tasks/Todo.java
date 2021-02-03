package duke.tasks;

/**
 * Represents Todo subclass of Task that contains description of Task.
 */
public class Todo extends Task {

    /**
     * Constructs Todo subclass of Task containing the description of the Task.
     * @param description description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns data representation of the Task, encoded to be saved in save file.
     * @return encoded form of Todo for save file.
     */
    @Override
    public String data() {
        return String.format("T | %s", super.data());
    }

    /**
     * Returns string representation of Todo.
     * @return type of Todo, whether it is done, description of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
