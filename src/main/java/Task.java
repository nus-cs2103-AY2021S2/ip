public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String status() {
        return (isDone ? "\u2713" : " ");
    }

    public String toString() {
        return this.name;
    }

    public String display() {
        return "[" + status() + "] " + this.name;
    }

    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n" + display();
    }

}
