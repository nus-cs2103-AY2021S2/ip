public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String status() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + status() + "] " + this.name;
    }

    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n" + toString();
    }

}
