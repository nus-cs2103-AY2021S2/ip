public class Task {
    private String description;
    private boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return blank or tick symbol
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
