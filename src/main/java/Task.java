public class Task {
    private boolean done;
    private String info;

    public Task(String info) {
        done = false;
        this.info = info;
    }

    public boolean getCompletionState() {
        return done;
    }

    public String getTaskInfo() {
        return info;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", done ? '✓' : '✗', info);
    }

    public void setTaskAsDone() {
        done = true;
    }
}