package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type = "";

    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " ");
    }

    public String getStatus() {
        return "[" + getStatusIcon() + "]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void getStatusAndTask() {
        System.out.println("       " + getStatus() + " " + this.description);
    }

    public char getType() {
        return type.charAt(1);
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "       " + this.getStatus() + " " + this.description;
    }
}