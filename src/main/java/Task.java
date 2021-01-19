public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick or X symbols
    }

    public String markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        String indicator = getStatusIcon();
        return "[" + indicator + "]  " + description;
    }

    public String displayTask() {
        String indicator = getStatusIcon();
        return "[" + indicator + "]  " + description;
    }
}
