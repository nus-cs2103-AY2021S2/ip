public class Task {
    private String title;
    private boolean isDone;

    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public Task(String title) {
        this(title, false);
    }

    public boolean ifDone() {
        return this.isDone;
    }

    /**
     * mark the task as done
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * @return title of the task with its status
     */
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + title;
    }
}
