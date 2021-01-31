package switchblade;

/**
 * switchblade.Task will be used as a parent-class for other sub-classes such as switchblade.Deadline and switchblade.myEvent
 *
 * @author leeyueyang
 */
public class Task {
    protected String description;
    protected boolean completed;

    public Task(String description) {
        this.description = description.stripLeading().stripTrailing();
        completed = false;
    }

    public Task(String description, Boolean completion) {
        this.description = description.stripLeading().stripTrailing();
        this.completed = completion;
    }

    /**
     * Mark this task as completed
     */
    public void markCompleted() {
        completed = true;
    }

    /**
     *
     * @return returns this task's protected description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return returns this task's protected completion status
     */
    public boolean getCompleted() {
        return completed;
    }

    /**
     *
     * @return includes an indication of [T] as it is a task and marks completion status of this task
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.completed) {
            sb.append("[T] [x] ");
        } else {
            sb.append("[T] [ ] ");
        }

        sb.append(this.description);
        return sb.toString();
    }
}
