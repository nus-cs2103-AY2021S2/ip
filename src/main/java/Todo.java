/**
 * A sub-class of Task to represents a toDo task key in by user.
 */
public class Todo extends Task{

    /**
     * Create a toDo task with given taskName attached.
     * @param taskName name of the task in String.
     */
    public Todo(String taskName) {
        super(taskName);

    }


    @Override
    public String toString() {
        return String.format("[T][%s] %d. %s", super.isDone(), super.getIndex(), super.getTaskName());
    }
}
