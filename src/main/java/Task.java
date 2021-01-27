public class Task {
    public static final String SHORTHAND = "INVALID";
    protected final String taskDescription;
    protected boolean isDone;


    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return this.isDone ? "X" : "";
    }

    public void markAsDone() {
        // TODO consider adding assertion here
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskDescription;
    }

}
