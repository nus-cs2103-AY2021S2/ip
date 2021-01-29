package DukeBody;

import DukeTask.*;
import java.util.ArrayList;

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
    public void markAsDone (int taskIndex) throws IndexOutOfBoundsException, 
            Task.MarkedAsDoneException {
        this.get(taskIndex).markAsDone();
    }
}
