public class Task {
    private String desc;
    private boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[x]" : "[ ]"); 
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + desc;
    }
}
