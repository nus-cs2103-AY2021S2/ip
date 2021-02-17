package duke.task;

/**
 * A subclass of Task that represent an event key in by user.
 */
public class Event extends Task {


    /**
     * Creates a event Task with given task name and given comments/dates.
     *
     * @param taskContent content of the user task in String.
     * @param date dates or comments of the user regarding the task.
     */
    public Event(String taskContent, String date) {
        super(taskContent, date);
    }

    public Event(String taskContent, String date, String done) {
        super(taskContent, date, done);
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
