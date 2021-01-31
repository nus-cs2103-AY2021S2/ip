package duke.task;
import java.util.ArrayList;

public class TaskList {
    /** An arraylist of tasks */
    public ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Class constructor specifying an arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Class constructor.
     */
    public TaskList() {
    }
}
