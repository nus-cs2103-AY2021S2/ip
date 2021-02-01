package duke.task;


/**
 * A class that store the task that user key in.
 */
public class Task {
    private final String taskName;
    private int index;
    private String isDone;
    private final String date;

    /**
     * Construct a task object with taskName attached and its index label in the taskList.
     *
     * @param taskName name of the task.
     */
    Task(String taskName) {
        this.taskName = taskName;
        this.index = TaskList.getTasksSize() + 1;
        this.isDone = " ";
        this.date = "";
    }

    Task(String taskName, String isDone, boolean check) {
        this.taskName = taskName;
        this.index = TaskList.getTasksSize() + 1;
        this.isDone = isDone;
        this.date = "";
    }

    Task(String taskName, String date) {
        this.taskName = taskName;
        this.index = TaskList.getTasksSize() + 1;
        this.isDone = " ";
        this.date = date;
    }

    Task(String taskName, String date, String done) {
        this.taskName = taskName;
        this.index = TaskList.getTasksSize() + 1;
        this.isDone = done;
        this.date = date;
    }


    public void changeIndex(int i) {
        this.index = i;
    }

    /**
     * Mark the task status as done with the notation X.
     */
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



    @Override
    public String toString() {
        return String.format("[%s] %d. %s", isDone, index, taskName);
    }
}
