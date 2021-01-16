/**
 * Encapsulates a task and supports operators to retrieve
 * information about the task such as id, taskName, status and type.
 */
public class Task {
    //tracks total number of tasks
    public static int numTasks = 0;

    //task details
    private final int id;
    private final String taskName;
    private String status;
    private final String type;

    /**
     * Constructor for Task class.
     * @param id id of task
     * @param taskName name of task
     * @param status task completion status
     * @param type type of task
     */
    public Task(int id, String taskName, String status, String type) {
        this.id = id;
        this.taskName = taskName;
        this.status = status;
        this.type = type;
        Task.numTasks += 1;
    }

    public int getId() {
        return this.id;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getStatus() {
        return this.status;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Marks the task as completed.
     */
    public void markCompleted() {
        this.status = "complete";
    }

    @Override
    public String toString() {
        if (this.getStatus().equals("incomplete")) {
            return "[" + this.getType() + "][ ] " + this.getTaskName();
        } else {
            return "[" + this.getType() + "][X] " + this.getTaskName();
        }
    }
}
