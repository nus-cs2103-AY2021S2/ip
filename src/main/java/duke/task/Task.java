package duke.task;


/**
 * A class that store the task that user key in.
 */
public class Task {
    private final String taskName;
    private static int capacity = 0;
    private int index;
    private String isDone;
    private final String date;

    /**
     * Construct a task object with taskName attached and its index label in the taskList.
     * @param taskName name of the task.
     */
    Task(String taskName) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.isDone = " ";
        this.date = "";
        capacity++;

    }

    Task(String taskName, String done, boolean check) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.isDone = done;
        this.date = "";
        capacity++;

    }

    Task(String taskName, String date) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.isDone = " ";
        this.date = date;
        capacity++;

    }

    Task(String taskName, String date, String done) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.isDone = done;
        this.date = date;
        capacity++;
    }


    public void changeIndex(int i) {
        this.index = i;
    }

    public void markDone() {
        this.isDone = "X";
    }



    /**
     * Get the name of the task.
     *
     * @return a String representation of the task name.
     */
    public String getTaskName() {
        return taskName;
    }


    /**
     * get the isDone status of the task.
     *
     * @return a String representation of the isDone status (X for done).
     */
    public String getDoneStatus() {
        return isDone;
    }

    /**
     * get the index label of the task.
     *
     * @return the int representation of the index label.
     */
    public int getIndex() {
        return index;
    }

    public int getType() {
        return 0;
    }

    public String getDate() {
        return date;
    }

    /**
     * get the current capacity of the taskList.
     *
     * @return the int representation of the capacity of the taskList.
     */
    public static int getCapacity() {
        return Task.capacity;
    }

    @Override
    public String toString() {
        return String.format("[%s] %d. %s", isDone, index, taskName);
    }
}