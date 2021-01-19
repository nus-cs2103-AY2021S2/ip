/**
 * Implementation for tasks with a specified end-date.
 */

public class Deadline extends Task {
    private final String lastDate;
    public Deadline(String taskName, String lastDate) {
        super(taskName);
        this.lastDate = lastDate;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.lastDate + ")";
    }
}
