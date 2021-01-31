abstract public class Task {
    private String title;
    private boolean isDone;

    /**
     * @param title the title of the task
     * @param isDone the status of the task
     */
    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
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
