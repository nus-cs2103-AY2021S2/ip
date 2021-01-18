public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void done() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        if(isDone) {
            return "[ ]";
        } else {
            return "[X]";
        }
    }
}
