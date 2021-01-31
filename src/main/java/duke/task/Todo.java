package duke.task;


/**
 * A sub-class of Task to represents a toDo task key in by user.
 */
public class Todo extends Task {

    /**
     * Create a Todo object with given taskName.
     *
     * @param taskName name of the task in String.
     */
    public Todo(String taskName) {
        super(taskName);

    }

    /**
     * Create a Todo object with given taskName and isDone status.
     * @param taskName name of the task.
     * @param done String representation of the done status.
     */
    public Todo(String taskName, String done) {
        super(taskName, done, true);

    }

    @Override
    public int getType() {
        return 1;
    }


    @Override
    public String toString() {
        return String.format("[T][%s] %d. %s", super.getDoneStatus(), super.getIndex(), super.getTaskName());
    }
}
