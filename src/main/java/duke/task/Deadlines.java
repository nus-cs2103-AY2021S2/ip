package duke.task;

/**
 * sub-class of Task to represents a task with deadline.
 */
public class Deadlines extends Task{



    /**
     * create a deadline task object with given task name and given dates/comments.
     *
     * @param taskName String representation of the name of the task.
     * @param date String representation of the comments/dates.
     */
    public Deadlines(String taskName, String date) {
        super(taskName, date);
    }

    public Deadlines(String taskName, String date, String done) {
        super(taskName, date, done);
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %d. %s ( %s )", super.getDoneStatus(), super.getIndex(),
                super.getTaskName(), super.getDate());
    }
}