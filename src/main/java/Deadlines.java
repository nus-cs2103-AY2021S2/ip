/**
 * sub-class of Task to represents a task with deadline.
 */
public class Deadlines extends Task{

    private String comments;

    /**
     * create a deadline task object with given task name and given dates/comments.
     *
     * @param taskName String representation of the name of the task.
     * @param comments String representation of the comments/dates.
     */
    public Deadlines(String taskName, String comments) {
        super(taskName);
        this.comments = comments;
    }


    @Override
    public String toString() {
        return String.format("[D][%s] %d. %s ( %s )", super.isDone(), super.getIndex(),
                super.getTaskName(), this.comments);
    }
}
