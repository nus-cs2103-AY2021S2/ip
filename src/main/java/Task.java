public class Task {
    protected String taskDescription;
    protected boolean done;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        done = false;
    }

    public void setDone() {
        done = true;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + taskDescription;
    }
}
