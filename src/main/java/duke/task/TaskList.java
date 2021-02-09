package duke.task;
import java.util.ArrayList;

public class TaskList {
    /** An arraylist of tasks */
    private ArrayList<Task> tasks;


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
        this.tasks = new ArrayList<>();
    }


    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;

    }
}
