import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class TaskList extends ArrayList<Task> {
    private static final long serialVersionUID = 6951591508963981354L;

    // accessors
    public ArrayList<Task> outstandingTasks () {
        ArrayList<Task> outstanding = new ArrayList<Task>();

        for (Task task: this) {
            if (! task.isDone()) {
                outstanding.add(task);
            }
        }

        return outstanding;
    }

    // mutators
    public void markAsDone (int taskIndex, DateTimeFormatter outputFormat) 
            throws IndexOutOfBoundsException {
        this.get(taskIndex - 1).markAsDone(outputFormat);
    }

    @Override
    public Task remove (int taskIndex) throws IndexOutOfBoundsException {
        return super.remove(taskIndex - 1);
    }
}
