package duke.task;

/**
 * A subclass of Task that represent an event key in by user.
 */
public class Event extends Task {


    /**
     * Create a event Task with given task name and given comments/dates.
     *
     * @param taskName name of the task.
     * @param date     dates or comments of the user regarding the task.
     */
    public Event(String taskName, String date) {
        super(taskName, date);
    }

    public Event(String taskName, String date, String done) {
        super(taskName, date, done);
    }

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %d. %s ( %s )", super.getDoneStatus(), super.getIndex(),
            super.getTaskName(), super.getDate());
    }
}
