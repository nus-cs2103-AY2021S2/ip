package duke.task;

import java.util.ArrayList;

public class TaskList {
    /**
     * An arraylist of tasks
     */
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

    /**
     * Gets the current tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the count current tasks.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Sets the status of the current task to completed.
     *
     * @param tasks the tasks in the taskList.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;

    }
}
