package core.task;

/**
 * A {@code Task} of type Todo, that does not have any dates associated with it.
 */
public class Todo extends Task {
    /**
     * Creates a {@code TodoTask} based on the description given.
     * @param desc - description.
     * @throws IllegalArgumentException
     */
    public Todo(String desc) throws IllegalArgumentException {
        super(desc.trim());
        if(this.taskDescription.isEmpty()) {
            throw new IllegalArgumentException("Empty Description for task !!");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
