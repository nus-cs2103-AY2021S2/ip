package duke.task;

/**
 * Todo extends from class Task and represent task that
 * the user is planning to do without a certain deadline.
 */
public class Todo extends Task{

    /**
     * Returns a Todo object that represent the task.
     *
     * @param description Description of the task.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Returns a string representation of the todo to be stored in the
     * hard disk.
     *
     * @return A String representing the task (in the form of "todo + description").
     */
    @Override
    String toFileString() {
        return String.format("todo %s", description);
    }

    @Override
    public String toString(){
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}
