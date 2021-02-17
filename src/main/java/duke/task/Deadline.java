package duke.task;

/**
 * sub-class of Task to represents a task with deadline.
 */
public class Deadline extends Task {


    /**
     * creates a deadline task object with given task name and given dates/comments.
     *
     * @param taskContent String representation of the content of the task.
     * @param date     String representation of the comments/dates.
     */
    public Deadline(String taskContent, String date) {
        super(taskContent, date);
    }

    public Deadline(String taskContent, String date, String done) {
        super(taskContent, date, done);
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
