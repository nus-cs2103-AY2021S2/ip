/**
 * This class handles the TodoTask.
 */
public class TodoTask extends Task {
    TodoTask(String task) {
        super(task);
    }

    @Override
    public String toString() {
        if (this.getStatus()) {
            return "[T][X] " + task;
        } else {
            return "[T][ ] " + task;
        }
    }
}
