package duke.task;
/**
 * This class handles the TodoTask.
 */
public class TodoTask extends Task {
    public TodoTask(String task) {
        super(task);
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[T][X] " + task;
        } else {
            return "[T][0] " + task;
        }
    }
}
