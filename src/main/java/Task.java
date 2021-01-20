public class Task {
    private String taskDescription;
    private boolean done;

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
