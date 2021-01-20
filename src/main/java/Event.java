/**
 * A subclass of Task that represent an event key in by user.
 */
public class Event extends Task{
    private String comments;

    /**
     * Create a event Task with given task name and given comments/dates.
     * @param taskName name of the task.
     * @param comments dates or comments of the user regarding the task.
     */
    public Event(String taskName, String comments) {
        super(taskName);
        this.comments = comments;
    }


    @Override
    public String toString() {
        return String.format("[E][%s] %d. %s ( %s )", super.isDone(), super.getIndex(),
                super.getTaskName(), this.comments);
    }
}
