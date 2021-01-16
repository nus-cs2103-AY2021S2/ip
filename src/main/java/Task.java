public class Task {
    public static int numTasks = 0;
    private final int id;
    private final String taskName;
    private String status;

    public Task(int id, String taskName, String status) {
        this.id = id;
        this.taskName = taskName;
        this.status = status;
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

    public void markCompleted() {
        this.status = "complete";
    }
}
