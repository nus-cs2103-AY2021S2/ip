package duke.task;

public class ToDo extends Task {
    /**
     * Creates a new ToDo.
     * @param name
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
